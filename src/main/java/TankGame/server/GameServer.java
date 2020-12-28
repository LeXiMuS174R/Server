package TankGame.server;

import TankGame.services.TanksService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {

    // соединение с первым игроком
    private Socket firstPlayer;
    // соединение со вторым игроком
    private Socket secondPlayer;

    private Long gameId;
    // потоки вычислений для обоих игроков
    private PlayerThread firstPlayerThread;
    private PlayerThread secondPlayerThread;

    private TanksService tanksService;

    // флаг начала игры
    private boolean isGameStarted = false;

    public GameServer(TanksService tanksService) {
        this.tanksService = tanksService;
    }

    public void start(int port) {
        try {
            // запустили сервер
            ServerSocket serverSocket = new ServerSocket(port);
            // запустили цикл ожидания подключения игроков
            while (!isGameStarted) {
                if (firstPlayer == null) {
                    // все приложение будет ожидать, пока не подключится первый игрок
                    firstPlayer = serverSocket.accept();
                    System.out.println("Первый игрок подключен");
                    // запускаем побочный поток для получения сообщений от первого игрока
                    firstPlayerThread = new PlayerThread(firstPlayer);
                    firstPlayerThread.playerValue = "PLAYER_1";
                    firstPlayerThread.start();
                } else {
                    // все приложение будет ожидать, пока не подключится второй игрок
                    secondPlayer = serverSocket.accept();
                    System.out.println("Второй игрок подключен");
                    // запускаем побочный поток для получения сообщений от второго игрока
                    secondPlayerThread = new PlayerThread(secondPlayer);
                    secondPlayerThread.playerValue = "PLAYER_2";
                    secondPlayerThread.start();
                    isGameStarted = true;
                }
            }
            // ожидать подключения клиентов
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    // поток для клиента
    private class PlayerThread extends Thread {
        // соединение с игроком
        private Socket player;

        // никнейм игрока
        private String nickname;
        // порядковый номер игрока
        private String playerValue;

        // поток для получения сообщений от игрока
        private BufferedReader fromClient;
        // поток для отправки сообщений игроку
        private PrintWriter toClient;

        public PlayerThread(Socket player) {
            this.player = player;
            try {
                this.fromClient = new BufferedReader(new InputStreamReader(player.getInputStream()));
                this.toClient = new PrintWriter(player.getOutputStream(), true);
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }

        // метод, который читает сообщения от клиента
        @Override
        public void run() {
            // читаем сообщения на протяжении всей игры
            while (true) {
                try {
                    String messageFromClient = fromClient.readLine();
                    if (messageFromClient != null) {
                        System.out.println("Получили сообщение от " + player.getInetAddress().getHostAddress() + ":" + player.getPort() + " - " + messageFromClient);

                        String command[] = messageFromClient.split(" ");

                        if (command[0].equals("start")) {
                            this.nickname = command[1];
                            // как проверить, что старт был от обоих игроков?
                            if (firstPlayerThread.nickname != null && secondPlayerThread.nickname != null) {
                                gameId = tanksService.startGame(firstPlayerThread.nickname, secondPlayerThread.nickname);
                                firstPlayerThread.toClient.println("PLAYER_1");
                                secondPlayerThread.toClient.println("PLAYER_2");
                            }
                        } else if (command[0].equals("shot")) {
                            if (command[1].equals("PLAYER_1")) {
                                tanksService.shot(gameId, firstPlayerThread.nickname, secondPlayerThread.nickname);
                            } else if (command[1].equals("PLAYER_2")) {
                                tanksService.shot(gameId, secondPlayerThread.nickname, firstPlayerThread.nickname);
                            }
                        }

                        firstPlayerThread.toClient.println(messageFromClient);
                        secondPlayerThread.toClient.println(messageFromClient);
                    }
                } catch (IOException e) {
                    throw new IllegalArgumentException(e);
                }

            }
        }
    }
}
