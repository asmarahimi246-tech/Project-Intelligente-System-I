package PACKAGE_NAME;

        Client client = new Client();
        ServerListener listener = new ServerListener(client);

        client.sendCommand("login " + playername);
        client.sendCommand("subscribe tic-tac-toe");

        String message;
        while ((message = client.getReader().readLine()) != null) {
            listener.Commandhandler(message);
            System.out.println("SERVER: " + message);
        }
    }
}
