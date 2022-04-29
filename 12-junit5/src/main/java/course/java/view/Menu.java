package course.java.view;

import course.java.controller.LoginController;
import course.java.exception.ConstraintViolationException;
import course.java.exception.InvalidEntityDataException;
import course.java.model.Role;

import java.util.*;
import java.util.stream.Collectors;

public class Menu {

    public static class Option {
        private String text;
        private Command command;

        private Set<Role> rolesAllowed = Set.of(Role.values());

        public Option(String text, Command command) {
            this.text = text;
            this.command = command;
        }

        public Option(String text, Command command, Set<Role> rolesAllowed) {
            this.text = text;
            this.command = command;
            this.rolesAllowed = rolesAllowed;
        }

        public String getText() {
            return text;
        }

        public Command getCommand() {
            return command;
        }

        public Set<Role> getRolesAllowed() {
            return rolesAllowed;
        }

        @Override
        public String toString() {
            return "MenuOption{" +
                    "text='" + text + '\'' +
                    ", command=" + command +
                    '}';
        }
    }

    // Main class methods and attributes.
    private String title;
    private List<Option> options;
    private LoginController loginController;
    private Scanner scanner = new Scanner(System.in);

    public Menu() {
    }

    public Menu(final String title, List<Option> options, LoginController loginController) {
        this.loginController = loginController;
//        menuTitle += ":";
        class ExitCommand implements Command {
            @Override
            public String execute() {
                return String.format("Exiting menu '%s'.", title);
            }
        }
        this.title = title;
        this.options = new ArrayList<>(options);
        this.options.add(new Option("Exit", new ExitCommand())); // add exit option as last option in menu
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;

        Menu menu = (Menu) o;

        if (getTitle() != null ? !getTitle().equals(menu.getTitle()) : menu.getTitle() != null) return false;
        return getOptions() != null ? getOptions().equals(menu.getOptions()) : menu.getOptions() == null;
    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + (getOptions() != null ? getOptions().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "title='" + title + '\'' +
                ", options=" + options +
                '}';
    }

    public void show() {
        while (true) {
            var optionsAllowed = options.stream()
                    .filter(option -> {
                        var loggedUser = loginController.getLoggedUser();
                        if (option.getRolesAllowed().contains(Role.ANONYMOUS)) {
                            return true;
                        }
                        if(loggedUser.isPresent()) {
                            return option.getRolesAllowed().contains(loggedUser.get().getRole());
                        }
                        return false;
                    }).collect(Collectors.toList());
            System.out.printf("\nMENU: %s%n", title);
            for (int i = 0; i < optionsAllowed.size(); i++) {
                System.out.printf("%2d. %s%n", i + 1, optionsAllowed.get(i).getText());
            }
//            var iter = options.listIterator(options.size());
//            var i = 1;
//            while (iter.hasPrevious()) {
//                System.out.printf("%2d. %s%n", i++, iter.previous().getText());
//            }
            int choice = -1;
            do {
                System.out.printf("Enter your choice (1 - %s):", optionsAllowed.size());
                var choiceStr = scanner.nextLine();
                try {
                    choice = Integer.parseInt(choiceStr);
                } catch (NumberFormatException ex) {
                    System.out.println("Error: Invalid choice. Please enter a valid number between 1 and " + optionsAllowed.size());
                }
            } while (choice < 1 || choice > optionsAllowed.size());
            String result = null;
            try {
                result = optionsAllowed.get(choice - 1).getCommand().execute();
                System.out.println(result);
            } catch (InvalidEntityDataException ex) {
                System.out.println("Error: " + ex.getMessage());
                if (ex.getCause() != null && ex.getCause() instanceof ConstraintViolationException) {
                    ((ConstraintViolationException) ex.getCause()).getFieldViolations().forEach(System.out::println);
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }

            if (choice == optionsAllowed.size()) { // Exit command chosen
                break;
            }
        }
    }
}
