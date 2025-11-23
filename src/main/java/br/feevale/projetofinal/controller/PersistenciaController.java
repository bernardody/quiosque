package br.feevale.projetofinal.controller;

import br.feevale.projetofinal.model.pedido.Pedido;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaController {

    private static final String ARQUIVO = "pedidos.dat";

    public static void salvarPedidos(List<Pedido> pedidos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(pedidos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar pedidos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Pedido> carregarPedidos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (List<Pedido>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de pedidos não encontrado, iniciando lista vazia.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar pedidos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}