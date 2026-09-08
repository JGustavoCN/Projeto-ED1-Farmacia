package br.edu.ifs.farmacia.persistence;

import br.edu.ifs.farmacia.model.login.Administrador;
import br.edu.ifs.farmacia.repository.UsuarioRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Classe para gerenciar a persistência de dados de usuários.
 * Os dados operacionais residem na pasta ./data/ e os seeds iniciais em resources.
 */
public class UsuarioDataManager {

    private static final String DATA_DIR = "data";
    private static final String FILE_NAME = "usuarios.dat";

    private static File getDataFile() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return new File(dir, FILE_NAME);
    }

    /**
     * Salva o repositório de usuários no arquivo especificado em ./data/.
     *
     * @param usuarioRepository O repositório de usuários a ser salvo.
     */
    public static void salvar(UsuarioRepository usuarioRepository) {
        if (usuarioRepository == null) {
            return;
        }
        File file = getDataFile();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(usuarioRepository);
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuários em " + file.getAbsolutePath() + ": " + e.getMessage());
        }
    }

    /**
     * Carrega o repositório de usuários.
     * Primeiro tenta ler de ./data/usuarios.dat. Caso não exista, carrega o seed
     * inicial de fábrica de resources e salva em ./data/. Se falhar, instancia repositório limpo.
     *
     * @return O repositório de usuários carregado ou novo repositório limpo.
     */
    public static UsuarioRepository carregar() {
        File file = getDataFile();

        // 1. Tenta carregar do arquivo de dados operacional em ./data/
        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                UsuarioRepository loadedRepository = (UsuarioRepository) ois.readObject();
                garantirAdmin(loadedRepository);
                return loadedRepository;
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao ler usuários de " + file.getAbsolutePath() + ": " + e.getMessage());
            }
        }

        // 2. Se não existir em ./data/, carrega a partir do seed de fábrica em resources
        try (InputStream inputStream = UsuarioDataManager.class.getClassLoader().getResourceAsStream("serialized_objects/" + FILE_NAME)) {
            if (inputStream != null) {
                try (ObjectInputStream ois = new ObjectInputStream(inputStream)) {
                    UsuarioRepository seedRepository = (UsuarioRepository) ois.readObject();
                    garantirAdmin(seedRepository);
                    salvar(seedRepository); // Persiste a cópia inicial em ./data/
                    return seedRepository;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar seed de fábrica de usuários: " + e.getMessage());
        }

        // 3. Fallback seguro sem recursão
        UsuarioRepository fallback = UsuarioRepository.createEmpty();
        garantirAdmin(fallback);
        return fallback;
    }

    private static void garantirAdmin(UsuarioRepository repository) {
        if (repository != null) {
            Administrador adm = new Administrador("admin", "admin");
            if (!repository.buscarTodos().contem(adm)) {
                repository.adicionar(adm);
            }
        }
    }
}
