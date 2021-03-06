package UserDataManager;

import CollectionClasses.*;
import CommandControl.CommandString;
import ValueControl.ValueException;
import Utility.EnumString;
import Utility.WorkerActions;
import Utility.WorkerValues;
import ValueControl.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

/**
 * Manage data from console
 */
public class UserConsole {
    public Input.InputWorker worker;
    public Input.InputCommand command;
    public Prints prints;

    public UserConsole(Scanner scannerConsole) {
        Input input = new Input(scannerConsole);
        worker = input.new InputWorker();
        command = input.new InputCommand();
        prints = new Prints();
    }
    /**
     * Only prints in console
     */
    public static class Prints {
        public void print(String text) {
            System.out.println(text);
        }
    }
    /**
     * Input and  possibility of output console
     */
    public static class Input {


        protected Scanner scannerConsole;

        private Input(Scanner scannerConsole) {
            this.scannerConsole = scannerConsole;
        }
        /**
         * Input user's worker data
         */
        public class InputWorker {
            /**
             * @return user's worker string which can be null
             */
            public String askWithNull() {
                String s = scannerConsole.nextLine();
                if (s == null || s.equals(""))
                    return null;
                return s;
            }
            /**
             * @return worker from console
             */
            public Worker askWorker() {
                Worker worker = new Worker();
                askAndSetName(worker);
                askAndSetCoordinates(worker);
                askAndSetSalary(worker);
                askAndSetPosition(worker);
                askAndSetStatus(worker);
                askAndSetOrganization(worker);
                return worker;
            }

            /**
             * set user's worker coordinates
             */
            private void askAndSetCoordinates(Worker worker) {
                System.out.println("???????? ??????????????????.");
                Coordinates coordinates = new Coordinates();
                if (WorkerValues.values.get("coordinates").containsKey(TypeOfControl.NOTNULL)) {
                    askAndSetXCoordinate(coordinates);
                    askAndSetYCoordinate(coordinates);
                    try {
                        worker.safeSetCoordinates(coordinates);
                    } catch (ValueException ignore){}
                }
                // Code for Null possible field
            }

            /**
             * set user's worker status
             */
            private void askAndSetStatus(Worker worker) {
                EnumString<Status> enumString = new EnumString<>("????????????", Status.values());
                System.out.println(enumString.toString());
                System.out.print(WorkerValues.getLimitsString("status"));
                try {
                    worker.safeSetStatus(enumString.getEnum(askWithNull()));
                } catch (ValueException e) {
                    if (e.getType() == TypeOfControl.NOTNULL) {
                        System.out.println("???????????????? ???? ?????????? ???????? ?????????? Null.");
                    }
                    askAndSetStatus(worker);
                } catch (IllegalArgumentException e) {
                    System.out.println("?????????????? ???????? ???? ???????????????????????? ????????????????");
                    askAndSetStatus(worker);
                }
            }
            /**
             * set user's worker address
             */
            private void askAndSetAddress(Organization organization) {
                System.out.println("???????? ?????????????????? ????????????.");
                Address address = new Address();
                if (WorkerValues.values.get("postalAddress").containsKey(TypeOfControl.NOTNULL)) {
                    askAndSetStreet(address);
                    askAndSetZipCode(address);
                    try {
                        organization.safeSetPostalAddress(address);
                    } catch (ValueException ignore){}
                }
                // Code for Null possible field
            }

            /**
             * set user's worker position
             */
            private void askAndSetPosition(Worker worker) {
                EnumString<Position> enumString = new EnumString<>("??????????????", Position.values());
                System.out.println(enumString.toString());
                System.out.print(WorkerValues.getLimitsString("position"));
                try {
                    worker.safeSetPosition(enumString.getEnum(askWithNull()));
                } catch (ValueException e) {
                    if (e.getType() == TypeOfControl.NOTNULL) {
                        System.out.println("???????????????? ???? ?????????? ???????? ?????????? Null.");
                    }
                    askAndSetPosition(worker);
                } catch (IllegalArgumentException e) {
                    System.out.println("?????????????? ???????? ???? ???????????????????????? ????????????????");
                    askAndSetPosition(worker);
                }
            }

            /**
             * set user's worker salary
             */
            private void askAndSetSalary(Worker worker) {
                System.out.println("?????????????? ????????????????. ?????????????????? ???????????? ???????????????? ????????????????.");
                System.out.print(WorkerValues.getLimitsString("salary"));
                try {
                    worker.safeSetSalary(Double.parseDouble(scannerConsole.nextLine()));
                } catch (ValueException valueException) {
                    if (valueException.getType() == TypeOfControl.NUMBER) {
                        System.out.println("???????????????? ?????? ??????????????????.");
                    }
                    askAndSetSalary(worker);
                } catch (NumberFormatException e) {
                    System.out.println("?????????? ???????????? ??????????!");
                    askAndSetSalary(worker);
                }
            }

            /**
             * set user's worker street
             */
            private void askAndSetStreet(Address address) {
                System.out.println("?????????????? ??????????. ");
                System.out.println(WorkerValues.getLimitsString("street"));
                try {
                    address.safeSetStreet(askWithNull());
                } catch (ValueException e) {
                    if (e.getType() == TypeOfControl.NOTNULL) {
                        System.out.println("???????? ???? ?????????? ???????? null!");
                    }
                    askAndSetStreet(address);
                }
            }

            /**
             * set user's worker zip code
             */
            private void askAndSetZipCode(Address address) {
                System.out.println("?????????????? zip ??????. ");
                System.out.println(WorkerValues.getLimitsString("zipCode"));
                try {
                    address.safeSetZipCode(askWithNull());
                } catch (ValueException e) {
                    if (e.getType() == TypeOfControl.NOTNULL) {
                        System.out.println("???????? ???? ?????????? ???????? null!");
                    }
                    if (e.getType() == TypeOfControl.STRING) {
                        System.out.println("?????????????????????? ?????????? ????????????!");
                    }
                    askAndSetZipCode(address);
                }
            }


            /**
             * set user's worker name
             */
            private void askAndSetName(Worker worker) {
                System.out.println("?????????????? ??????. ");
                System.out.print(WorkerValues.getLimitsString("name"));
                try {
                    worker.safeSetName(askWithNull());
                } catch (ValueException valueException) {
                    if (valueException.getType() == TypeOfControl.STRING) {
                        System.out.println("?????????? ?????????????????? ???????????? ?????? ??????????????????.");
                    }
                    if (valueException.getType() == TypeOfControl.NOTNULL) {
                        System.out.println("???????? ???? ?????????? ???????? null!");
                    }
                    askAndSetName(worker);

                }
            }

            /**
             * set user's worker X coordinate
             */
            private void askAndSetXCoordinate(Coordinates coordinates) {
                System.out.println("?????????????? ???????????????????? X. ?????????????????? ???????????? ???????????????? ????????????????.");
                System.out.print(WorkerValues.getLimitsString("x"));
                try {
                    coordinates.safeSetX(Long.parseLong(scannerConsole.nextLine()));
                } catch (NumberFormatException e) {
                    System.out.println("?????????? ???????????? ??????????!");
                    askAndSetXCoordinate(coordinates);
                } catch (ValueException valueException) {
                    if (valueException.getType() == TypeOfControl.NUMBER) {
                        System.out.println("?????????????????? ???????????????? ?????? ??????????????????.");
                    }
                    askAndSetXCoordinate(coordinates);
                }

            }

            /**
             * set user's worker Y coordinate
             */
            private void askAndSetYCoordinate(Coordinates coordinates) {
                System.out.print("?????????????? ???????????????????? Y. ?????????????????? ???????????? ???????????????? ????????????????. -> ");
                try {
                    coordinates.safeSetY(Long.parseLong(scannerConsole.nextLine()));
                } catch (NumberFormatException e) {
                    System.out.println("?????????? ???????????? ??????????!");
                    askAndSetXCoordinate(coordinates);
                } catch (ValueException ignored) {
                }
            }

            /**
             * set user's worker annual turnover
             */
            private void askAndSetAnnualTurnover(Organization organization) {
                System.out.println("?????????????? ?????????????????? ????????????.");
                System.out.println(WorkerValues.getLimitsString("annualTurnover"));
                try {
                    String st = askWithNull();
                    organization.safeSetAnnualTurnover((st != null) ? Float.parseFloat(st) : null);
                } catch (NumberFormatException e) {
                    System.out.println("?????????? ???????????? ??????????!");
                    askAndSetAnnualTurnover(organization);
                } catch (ValueException e) {
                    if (e.getType() == TypeOfControl.NOTNULL) {
                        System.out.println("???????????????? ???? ?????????? ???????? Null.");
                    }
                    if (e.getType() == TypeOfControl.NUMBER) {
                        System.out.println("?????????????????? ???????????????? ?????? ??????????????????.");
                    }
                    askAndSetAnnualTurnover(organization);
                }
            }

            /**
             * set user's worker organization
             */
            private void askAndSetOrganization(Worker worker) {
                System.out.println("???????? ??????????????????????.");
                Organization organization = new Organization();
                if (WorkerValues.values.get("organization").containsKey(TypeOfControl.NOTNULL)) {
                    askAndSetAnnualTurnover(organization);
                    askAndSetAddress(organization);
                    try {
                        worker.safeSetOrganization(organization);
                    } catch (ValueException ignore){}
                    return;
                }
                System.out.println("???????? ???? ???????????? ?????????????????? ????????????????, ???????????????? ???? Null, ?????????????? ?????????? ????????????, ?????????? ????????????.");
                if (askWithNull() == null) {
                    try {
                        worker.safeSetOrganization(null);
                    } catch (ValueException ignore) {
                    }
                    return;
                }
                askAndSetAnnualTurnover(organization);
                askAndSetAddress(organization);
            }
        }

        /**
         * Input user's command and its arguments
         */
        public class InputCommand {
            /**
             * @return user's command and arguments
             */
            public CommandString askCommandAndArguments() {
                System.out.print("?????????????? ?????????????? ??/?????? ??????????????????????/???? ->");
                String[] array = scannerConsole.nextLine().split(" ");
                if (array.length == 0) {
                    System.out.print("?????????????? ??????????????! -> ");
                    return askCommandAndArguments();
                }
                if ((array.length == 2 && array[1].equals("")) || (array.length == 1)) {
                    return new CommandString(array[0], null);
                }
                return new CommandString(array[0], Arrays.copyOfRange(array, 1, array.length));
            }

            /**
             * @return user's arguments
             */
            public CommandString askArguments(String commandString) {
                System.out.print("?????????????? ???????????? ??????????????????! ->");
                String[] array = scannerConsole.nextLine().split(" ");
                if (array.length == 0)
                    return new CommandString(commandString, null);
                return new CommandString(commandString, array);
            }
        }

    }
}
