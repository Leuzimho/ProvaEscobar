package com.mycompany.estoquemonitoramento;

import java.util.ArrayList;
import java.util.List;

// Interface Observer (Observador)
interface Observer {
    void update(String productName, int quantity);
}

// Classe Produto (Subject/Observable)
class Produto {
    private String name;
    private int quantity;
    private final List<Observer> observers = new ArrayList<>();

    public Produto(String name, int initialQuantity) {
        this.name = name;
        this.quantity = initialQuantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    
    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(name, quantity);
        }
    }

    
    public void updateQuantity(int newQuantity) {
        this.quantity = newQuantity;
        notifyObservers(); // Notifica os observadores sempre que houver mudança
    }
}


class DataAnalysisSystem implements Observer {
    @Override
    public void update(String productName, int quantity) {
        System.out.println("DataAnalysisSystem: O produto " + productName + " agora possui " + quantity + " unidades.");
    }
}


class TeamNotificationSystem implements Observer {
    @Override
    public void update(String productName, int quantity) {
        System.out.println("TeamNotificationSystem: Notificacao para a equipe: O produto " + productName + " possui " + quantity + " unidades.");
    }
}

// Classe Principal para teste
public class EstoqueMonitoramento {
    public static void main(String[] args) {
        
        Produto produto1 = new Produto("Cadeira", 50);
        Produto produto2 = new Produto("Mesa", 20);

        
        DataAnalysisSystem dataSystem = new DataAnalysisSystem();
        TeamNotificationSystem teamSystem = new TeamNotificationSystem();

        
        produto1.addObserver(dataSystem);
        produto1.addObserver(teamSystem);

        produto2.addObserver(dataSystem);

        // Simulação de mudanças nos estoques
        System.out.println("Atualizando estoques...");
        produto1.updateQuantity(45); // Notifica DataAnalysisSystem e TeamNotificationSystem
        produto2.updateQuantity(18); // Notifica apenas DataAnalysisSystem

        // Removendo um observador e simulando outra atualização
        System.out.println("\nRemovendo um observador...");
        produto1.removeObserver(teamSystem);
        produto1.updateQuantity(40); // Notifica apenas DataAnalysisSystem
    }
}
