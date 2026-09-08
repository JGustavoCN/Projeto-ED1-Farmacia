package br.edu.ifs.farmacia.persistence;

import br.edu.ifs.farmacia.repository.VendaRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Classe para gerenciar a persistência de dados de vendas.
 * Os dados operacionais residem na pasta ./data/ e os seeds iniciais em resources.
 */
public class VendaDataManager {

    private static final String DATA_DIR = "data";
    private static final String FILE_NAME = "vendas.dat";

    private static File getDataFile() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return new File(dir, FILE_NAME);
    }

    /**
     * Salva o repositório de vendas no arquivo especificado em ./data/.
     *
     * @param vendaRepository O repositório de vendas a ser salvo.
     */
    public static void salvar(VendaRepository vendaRepository) {
        if (vendaRepository == null) {
            return;
        }
        File file = getDataFile();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(vendaRepository);
        } catch (IOException e) {
            System.err.println("Erro ao salvar vendas em " + file.getAbsolutePath() + ": " + e.getMessage());
        }
    }

    /**
     * Carrega o repositório de vendas.
     * Primeiro tenta ler de ./data/vendas.dat. Caso não exista, carrega o seed
     * inicial de fábrica de resources e salva em ./data/. Se falhar, instancia repositório limpo.
     *
     * @return O repositório de vendas carregado ou novo repositório limpo.
     */
    public static VendaRepository carregar() {
        File file = getDataFile();

        // 1. Tenta carregar do arquivo operacional em ./data/
        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return (VendaRepository) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao ler vendas de " + file.getAbsolutePath() + ": " + e.getMessage());
            }
        }

        // 2. Se não existir em ./data/, carrega a partir do seed de fábrica em resources
        try (InputStream inputStream = VendaDataManager.class.getClassLoader().getResourceAsStream("serialized_objects/" + FILE_NAME)) {
            if (inputStream != null) {
                try (ObjectInputStream ois = new ObjectInputStream(inputStream)) {
                    VendaRepository seedRepository = (VendaRepository) ois.readObject();
                    salvar(seedRepository); // Persiste a cópia inicial em ./data/
                    return seedRepository;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar seed de fábrica de vendas: " + e.getMessage());
        }

        // 3. Fallback seguro sem recursão
        return VendaRepository.createEmpty();
    }
}
