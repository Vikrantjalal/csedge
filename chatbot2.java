import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class chatbot2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Hello! I am your assistant chatbot. Type 'exit' to end the conversation.");
        System.out.println("You can ask me to open applications or search the web.");
        System.out.println("For example:");
        System.out.println(" - open notepad");
        System.out.println(" - search ");
        
        while (true) {
            System.out.print("user: ");
            input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("exit")){
                System.out.println("Chatbot: Goodbye!");
                break;
            }
            
            handleCommand(input);
        }
        
        scanner.close();
    }
    
    public static void handleCommand(String input) {
        if (input.toLowerCase().startsWith("open ")) {
            String application = input.substring(5).toLowerCase();
            openApplication(application);
        } else if (input.toLowerCase().startsWith("search ")) {
            String query = input.substring(7);
            searchWeb(query);
        } else {
            System.out.println("Chatbot: I'm not sure how to respond to that.");
        }
    }
    
    public static void openApplication(String application) {
        try {
            switch (application) {
                case "notepad":
                    Runtime.getRuntime().exec("notepad");
                    System.out.println("Chatbot: Opening Notepad...");
                    break;
                case "calculator":
                    Runtime.getRuntime().exec("clac");
                    System.out.println("Chatbot: Opening Calculator...");
                    break;
                default:
                    System.out.println("Chatbot: Application not recognized.");
                    break;
            }
        }catch(IOException e){
            System.out.println("chatbot:failed to open the application.");
            e.printStackTrace();
        }
    }
    
    public static void searchWeb(String query) {
        String url = "https://www.google.com/search?q=euro+2024&oq=&gs_lcrp=EgZjaHJvbWUqCQgBECMYJxjqAjIJCAAQIxgnGOoCMgkIARAjGCcY6gIyCQgCECMYJxjqAjIJCAMQIxgnGOoCMgkIBBAjGCcY6gIyCQgFEC4YJxjqAjIJCAYQIxgnGOoCMgkIBxAjGCcY6gLSAQkyMTQwajBqMTWoAgiwAgE&sourceid=chrome&ie=UTF-8" + query.replace(" ", "+");
        try {
            java.awt.Desktop.getDesktop().browse(new URI(url));
            System.out.println("Chatbot: Searching for '" + query + "' on the web...");
        } catch (IOException | URISyntaxException e) {
            System.out.println("Chatbot: Failed to open web browser.");
            e.printStackTrace();
        }
    }
}

