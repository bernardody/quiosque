package br.feevale.projetofinal.controller;

import br.feevale.projetofinal.model.Estabelecimento;
import java.io.*;

public class PersistenciaController {
    
    private static final String ARQUIVO = "estabelecimento.dat";
    
    public static void salvar(Estabelecimento estabelecimento) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(estabelecimento);
        } catch (IOException e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
        }
    }
    
    public static Estabelecimento carregar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (Estabelecimento) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado, criando novo estabelecimento.");
            return null;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar: " + e.getMessage());
            return null;
        }
    }
}