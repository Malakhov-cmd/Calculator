package Calculator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class MainWindow extends Application implements Initializable {

    public Menu font;
    public CheckMenuItem bold;
    public CheckMenuItem italic;
    public MenuItem fontName;
    public MenuItem fontWeight;

    public Menu coloursMenu;
    public RadioMenuItem black;
    public RadioMenuItem red;
    public RadioMenuItem purple;
    public RadioMenuItem yellow;

    public MenuBar bar;
    public Menu settings;
    public MenuItem exit;
    public Menu info;
    public MenuItem aboutProgram;
    public MenuItem aboutProgrammist;

    public TextArea textArea;
    public Line colourLine;

    public Button roundMath;
    public Button pow;
    public Button factorial;
    public Button changeSign;
    public Button clearTextArea;
    public Button removeLastSymbol;
    public Button division;

    public Button asin;
    public Button sin;
    public Button significant;
    public Button seven;
    public Button eighth;
    public Button nine;
    public Button multiply;

    public Button acos;
    public Button cos;
    public Button sqrt;
    public Button four;
    public Button five;
    public Button six;
    public Button minus;

    public Button atan;
    public Button tan;
    public Button ln;
    public Button one;
    public Button two;
    public Button three;
    public Button plus;

    public Button lg;
    public Button pi;
    public Button e;
    public Button zero;
    public Button point;
    public Button equally;

    private Pattern patternNumToNum = Pattern.compile("(\\+|\\-)?((e|pi)|(\\d+\\.?\\d*))(\\+|\\/|\\*|\\-)(\\+|\\-)?((e|pi)|(\\d+\\.?\\d*))(\\+|\\/|\\*|\\-|\\=|\\~)");
    private Pattern patternNumToFunk = Pattern.compile("(\\+|\\-)?((e|pi)|(\\d+\\.?\\d*))(\\+|\\/|\\*|\\-)(\\+|\\-)?((pow\\((\\+|\\-)?((e|pi)|(\\d+\\.?\\d*))\\,((\\+|\\-)?(e|pi)|(\\d+\\.?\\d*))\\))|(asin\\((\\+|\\-)?((e|pi)|(\\d+\\.?\\d*))\\))|(sin\\((\\+|\\-)?((e|pi)|(\\d+\\.?\\d*))\\))|(acos\\((\\+|\\-)?((e|pi)|(\\d+\\.?\\d*))\\))|(cos\\((\\+|\\-)?((e|pi)|(\\d+\\.?\\d*))\\))|(sqrt\\(((e|pi)|(\\d+\\.?\\d*))\\))|(tan\\((\\+|\\-)?((e|pi)|(\\d+\\.?\\d*))\\))|(atan\\((\\+|\\-)?((e|pi)|(\\d+\\.?\\d*))\\))|(ln\\(((e|pi)|(\\d+\\.?\\d*))\\))|(lg\\(((e|pi)|(\\d+\\.?\\d*))\\))|(significant\\(((e|pi)|(\\d+\\.?\\d*))\\)))(\\+|\\/|\\*|\\-|\\=|\\~)");
    private Pattern patternNumToFactorial = Pattern.compile("(\\+|\\-)?((e|pi)|(\\d+\\.?\\d*))(\\+|\\/|\\*|\\-)(\\+|\\-)?((\\d+)\\!)(\\+|\\/|\\*|\\-|\\=|\\~)");
    private Pattern patternStartFunk = Pattern.compile("(\\+|\\-)?((pow\\((\\+|\\-)?((e|pi)|(\\d+\\.?\\d*))\\,((\\+|\\-)?(e|pi)|(\\d+\\.?\\d*))\\))|(asin\\((\\+|\\-)?((e|pi)|(\\d+\\.?\\d*))\\))|(sin\\((\\+|\\-)?((e|pi)|(\\d+\\.?\\d*))\\))|(acos\\((\\+|\\-)?((e|pi)|(\\d+\\.?\\d*))\\))|(cos\\((\\+|\\-)?((e|pi)|(\\d+\\.?\\d*))\\))|(sqrt\\(((e|pi)|(\\d+\\.?\\d*))\\))|(tan\\((\\+|\\-)?((e|pi)|(\\d+\\.?\\d*))\\))|(atan\\((\\+|\\-)?((e|pi)|(\\d+\\.?\\d*))\\))|(ln\\(((e|pi)|(\\d+\\.?\\d*))\\))|(lg\\(((e|pi)|(\\d+\\.?\\d*))\\))|(significant\\(((e|pi)|(\\d+\\.?\\d*))\\)))(\\+|\\/|\\*|\\-|\\=|\\~)");
    private Pattern patternStartFactorial = Pattern.compile("(\\+|\\-)?(\\d+)\\!(\\+|\\/|\\*|\\-|\\=|\\~)");
    private Pattern patternStartNumber = Pattern.compile("(\\+|\\-)?((e|pi)|(\\d+\\.?\\d*))(\\+|\\/|\\*|\\-|\\=|\\~)");

    private Pattern patternAlert = Pattern.compile("(\\+|\\-)?(\\S+|(S+\\(\\S+\\)))(\\+|\\/|\\*|\\-|\\=)(\\S+|(S+\\(\\S+\\)))(\\+|\\/|\\*|\\-|\\=|\\~)");

    private double firstValue;
    private double secondValue;
    private boolean indexPlusMinus;
    private char action;

    private String fontType;
    private String fontWeightSTR = "12";

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("VisualizationOfPageElements.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("Calculator/stylesheets.css");
        stage.setScene(scene);
        stage.getIcons().add(new Image("Calculator/Icon.png"));
        stage.setTitle("Calculator");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textArea.textProperty().addListener((actionEvent) -> {
                    boolean firstSign = false;
                    textArea.requestFocus();

                    for (int i = 0; i < textArea.getLength(); i++) {
                        if ((textArea.getText().charAt(i) == '+' || textArea.getText().charAt(i) == '-' || textArea.getText().charAt(i) == '*' || textArea.getText().charAt(i) == '/' || textArea.getText().charAt(i) == '=' || textArea.getText().charAt(i) == '~') && i != 0 && !firstSign) {
                            action = textArea.getText().charAt(i);
                            firstSign = true;
                        }
                    }

                    if (checkCorrectEnter() != 0) {
                        switch (action) {
                            case '+' -> plusAction();
                            case '-' -> minusAction();
                            case '*' -> multiplyAction();
                            case '/' -> devideAction();
                            case '=' -> equallyAction();
                            case '~' -> roundAction();
                        }
                        indexPlusMinus = false;
                    } else {
                        if (patternAlert.matcher(textArea.getText()).matches() && !textArea.getText(textArea.getLength() - 2, textArea.getLength()).equals("(-")) {
                            alert();
                        }
                    }
                }
        );

        coloursMenu.setOnAction(actionEvent -> {
            Scene scene = textArea.getScene();
            if (red.isSelected()) {
                scene.getStylesheets().removeAll(scene.getStylesheets());
                scene.getStylesheets().add("Calculator/redEdition.css");
            } else {
                if (black.isSelected()) {
                    scene.getStylesheets().removeAll(scene.getStylesheets());
                    scene.getStylesheets().add("Calculator/blackEdition.css");
                } else {
                    if (purple.isSelected()) {
                        scene.getStylesheets().removeAll(scene.getStylesheets());
                        scene.getStylesheets().add("Calculator/purpleEdition.css");
                    } else {
                        if (yellow.isSelected()) {
                            scene.getStylesheets().removeAll(scene.getStylesheets());
                            scene.getStylesheets().add("Calculator/stylesheets.css");
                        }
                    }
                }
            }
        });


        EventHandler<ActionEvent> listener = event -> {
            if (fontWeightSTR.matches("([1]?[0-7])")) {
                Font font = Font.font(fontType, bold.isSelected() ? FontWeight.BOLD : FontWeight.NORMAL, italic.isSelected() ? FontPosture.ITALIC : FontPosture.REGULAR, Double.parseDouble(fontWeightSTR));
                textArea.setFont(font);
                System.out.println(fontWeightSTR);
                textArea.positionCaret(textArea.getLength());
            }
        };

        bold.setOnAction(listener);
        bold.setAccelerator(KeyCombination.keyCombination("Shortcut+B"));
        italic.setOnAction(listener);
        italic.setAccelerator(KeyCombination.keyCombination("Shortcut+C"));

        fontName.setOnAction(actionEvent -> {
            ChoiceDialog<String> dialog = new ChoiceDialog<>("System", Font.getFamilies());
            dialog.setHeaderText("Pick a font.");
            dialog.showAndWait().ifPresentOrElse(
                    result -> fontType = dialog.getSelectedItem(),
                    () -> fontType = "System Regular"
            );
            System.out.println("w" + fontWeightSTR);
            if (fontWeightSTR.matches("([1]?[0-7])")) {
                Font font = Font.font(fontType, bold.isSelected() ? FontWeight.BOLD : FontWeight.NORMAL, italic.isSelected() ? FontPosture.ITALIC : FontPosture.REGULAR, Double.parseDouble(fontWeightSTR));
                textArea.setFont(font);
                textArea.positionCaret(textArea.getLength());
            }
        });

        fontWeight.setOnAction(event ->
        {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Pick a font Weight");
            dialog.showAndWait().ifPresentOrElse(
                    result -> fontWeightSTR = result,
                    () -> fontWeightSTR = "12");
            System.out.println("w " + fontWeightSTR);
            if (fontWeightSTR.matches("([1]?[0-7])")) {
                Font font = Font.font(fontType, bold.isSelected() ? FontWeight.BOLD : FontWeight.NORMAL, italic.isSelected() ? FontPosture.ITALIC : FontPosture.REGULAR, Double.parseDouble(fontWeightSTR));
                textArea.setFont(font);
                textArea.positionCaret(textArea.getLength());
            }
        });

        aboutProgram.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Solutions used when creating this calculator");
            alert.setHeaderText("Technical information");

            File imageBomb = new File("E:\\Calculator\\src\\Calculator\\infoSecond.png");
            String placeURL = null;
            try {
                placeURL = imageBomb.toURI().toURL().toString();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Image image = new Image(placeURL);
            alert.setGraphic(new ImageView(image));

            TextArea areaTXTError = new TextArea("The calculator supports the calculation of numbers both simple and real. " + "\n"
                                                    + "In addition, it provides support for many functions and factorial calculation." + "\n" +
                                                    "Checking the sanity of entering an expression is provided using regular expressions." + "\n" +
                                                    "Then we get the value of the sign from the resulting string. After that we call the" + "\n" +
                                                    "method that implements working with this sign");

            alert.getDialogPane().setExpandableContent(areaTXTError);
            alert.showAndWait();
        });
        aboutProgram.setAccelerator(KeyCombination.keyCombination("Shortcut+P"));

        aboutProgrammist.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Malakhov Georgey, 6302");
            alert.showAndWait();
        });
        aboutProgrammist.setAccelerator(KeyCombination.keyCombination("Shortcut+I"));

        zero.setOnAction(actionEvent -> {
            textArea.appendText("0");
        });
        one.setOnAction(actionEvent -> {
            textArea.appendText("1");
        });
        two.setOnAction(actionEvent -> {
            textArea.appendText("2");
        });
        three.setOnAction(actionEvent -> {
            textArea.appendText("3");
        });
        four.setOnAction(actionEvent -> {
            textArea.appendText("4");
        });
        five.setOnAction(actionEvent -> {
            textArea.appendText("5");
        });
        six.setOnAction(actionEvent -> {
            textArea.appendText("6");
        });
        seven.setOnAction(actionEvent -> {
            textArea.appendText("7");
        });
        eighth.setOnAction(actionEvent -> {
            textArea.appendText("8");
        });
        nine.setOnAction(actionEvent -> {
            textArea.appendText("9");
        });
        point.setOnAction(actionEvent -> {
            textArea.appendText(".");
        });
        e.setOnAction(actionEvent -> {
            textArea.appendText("e");
        });
        pi.setOnAction(actionEvent -> {
            textArea.appendText("pi");
        });
        removeLastSymbol.setOnAction(actionEvent -> {
            textArea.deleteText(textArea.getLength() - 1, textArea.getLength());
        });
        roundMath.setOnAction(actionEvent -> {
            textArea.appendText("~");
        });
        clearTextArea.setOnAction(actionEvent -> {
            textArea.clear();
            firstValue = 0;
            secondValue = 0;
            indexPlusMinus = false;
            action = '\u0000';
        });
        pow.setOnAction(actionEvent -> {
            textArea.appendText("pow(,)");
            textArea.positionCaret(textArea.getLength() - 2);
        });
        factorial.setOnAction(actionEvent -> {
            textArea.appendText("!");
        });
        changeSign.setOnAction(actionEvent -> {
            StringBuilder stringBuilder = new StringBuilder(textArea.getText());
            if (textArea.getText().startsWith("-")) {
                stringBuilder.delete(0, 1);
            } else {
                stringBuilder.replace(0, 0, "-");
            }
            textArea.setText(stringBuilder.toString());
            textArea.positionCaret(textArea.getLength());
        });
        asin.setOnAction(actionEvent -> {
            textArea.appendText("asin()");
            textArea.positionCaret(textArea.getLength() - 1);
        });
        sin.setOnAction(actionEvent -> {
            textArea.appendText("sin()");
            textArea.positionCaret(textArea.getLength() - 1);
        });
        significant.setOnAction(actionEvent -> {
            textArea.appendText("significant()");
            textArea.positionCaret(textArea.getLength() - 1);
        });
        acos.setOnAction(actionEvent -> {
            textArea.appendText("acos()");
            textArea.positionCaret(textArea.getLength() - 1);
        });
        cos.setOnAction(actionEvent -> {
            textArea.appendText("cos()");
            textArea.positionCaret(textArea.getLength() - 1);
        });
        sqrt.setOnAction(actionEvent -> {
            textArea.appendText("sqrt()");
            textArea.positionCaret(textArea.getLength() - 1);
        });
        atan.setOnAction(actionEvent -> {
            textArea.appendText("atan()");
            textArea.positionCaret(textArea.getLength() - 1);
        });
        tan.setOnAction(actionEvent -> {
            textArea.appendText("tan()");
            textArea.positionCaret(textArea.getLength() - 1);
        });
        ln.setOnAction(actionEvent -> {
            textArea.appendText("ln()");
            textArea.positionCaret(textArea.getLength() - 1);
        });
        lg.setOnAction(actionEvent -> {
            textArea.appendText("lg()");
            textArea.positionCaret(textArea.getLength() - 1);
        });
        plus.setOnAction(actionEvent -> {
            textArea.appendText("+");
        });
        minus.setOnAction(actionEvent -> {
            textArea.appendText("-");
        });
        multiply.setOnAction(actionEvent -> {
            textArea.appendText("*");
        });
        division.setOnAction(actionEvent -> {
            textArea.appendText("/");
        });
        equally.setOnAction(actionEvent -> {
            textArea.appendText("=");
        });

    }

    private int checkCorrectEnter() {
        if (patternNumToNum.matcher(textArea.getText()).matches()) {
            return 1;
        } else {
            if (patternNumToFunk.matcher(textArea.getText()).matches()) {
                return 2;
            } else {
                if (patternStartFunk.matcher(textArea.getText()).matches()) {
                    return 3;
                } else {
                    if (patternStartFactorial.matcher(textArea.getText()).matches()) {
                        return 4;
                    } else {
                        if (patternStartNumber.matcher(textArea.getText()).matches()) {
                            return 5;
                        } else {
                            if (patternNumToFactorial.matcher(textArea.getText()).matches()) {
                                return 6;
                            }
                        }
                    }
                }
            }
            return 0;
        }
    }

    private void alert() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Check correct variants and try again");
        alert.setHeaderText("Error input");

        File imageBomb = new File("C:\\Users\\Георгий Малахов\\IdeaProjects\\FX\\src\\DialogFileChooser\\bomb.png");
        String placeURL = null;
        try {
            placeURL = imageBomb.toURI().toURL().toString();
        } catch (MalformedURLException malformedURLException) {
            malformedURLException.printStackTrace();
        }
        Image image = new Image(placeURL);
        alert.setGraphic(new ImageView(image));

        TextArea areaTXTError = new TextArea("Examples of correct expression input" + "\n" +
                                                "1+2" + "\n" +
                                                "1.01+-5" + "\n" +
                                                "1.002--sin(5)" + "\n" +
                                                "1+sqrt(16)" + "\n" +
                                                "4!" + "\n" +
                                                "15+pow(2,2)");

        alert.getDialogPane().setExpandableContent(areaTXTError);
        alert.showAndWait();
    }

    private int calculateFactorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    private int findBracketsOrActionSign(String expression) {
        int indexBrackets = 0;
        if (expression.startsWith("-")) {
            indexPlusMinus = true;
        }
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(' || (expression.charAt(i) == '!')) {
                indexBrackets = i;
            }
        }
        return indexBrackets;
    }

    private double findSecondNumber() {
        int index = 0;
        boolean first = false;
        for (int i = 0; i < textArea.getText().length(); i++) {
            if ((textArea.getText().charAt(i) == '+' || textArea.getText().charAt(i) == '-' || textArea.getText().charAt(i) == '*' || textArea.getText().charAt(i) == '/' || textArea.getText().charAt(i) == '=' || textArea.getText().charAt(i) == '~') && !first) {
                if (i != 0) {
                    if (textArea.getText().charAt(i + 1) == '-') {
                        index = i + 1;
                        System.out.println("index " + index);
                        return Double.parseDouble(textArea.getText(index, textArea.getLength() - 1));
                    } else {
                        index = i;
                        first = true;
                    }
                }
            } else {
                if (textArea.getText().contains("e")) {
                    return Math.E;
                } else {
                    if (textArea.getText().contains("pi")) {
                        return Math.PI;
                    }
                }
            }
        }
        return Double.parseDouble(textArea.getText(index + 1, textArea.getLength() - 1));
    }

    private String findSecondValue() {
        int index = 0;
        boolean first = false;
        for (int i = 0; i < textArea.getText().length(); i++) {
            if ((textArea.getText().charAt(i) == '+' || textArea.getText().charAt(i) == '-' || textArea.getText().charAt(i) == '*' || textArea.getText().charAt(i) == '/' || textArea.getText().charAt(i) == '=' || textArea.getText().charAt(i) == '~') && !first && i != 0) {
                index = i;
                first = true;
            }
        }
        return textArea.getText(index + 1, textArea.getLength());
    }

    private double findFirstNumber() {
        int index = 0;
        for (int i = 0; i < textArea.getText().length(); i++) {
            if (textArea.getText().charAt(i) == '+' || (textArea.getText().charAt(i) == '-') || (textArea.getText().charAt(i) == '*') || (textArea.getText().charAt(i) == '/') || (textArea.getText().charAt(i) == '=') || textArea.getText().charAt(i) == '~') {
                index = i;
            } else {
                if (textArea.getText().contains("e")) {
                    return Math.E;
                } else {
                    if (textArea.getText().contains("pi")) {
                        return Math.PI;
                    }
                }
            }
        }
        return Double.parseDouble(textArea.getText(0, index));
    }

    private ArrayList<Double> getArguments(String arguments) {
        ArrayList<Double> listResult = new ArrayList<>();
        StringBuffer argument1 = new StringBuffer();
        StringBuffer argument2 = new StringBuffer();
        boolean secondArgument = false;

        for (int i = 0; i < arguments.length(); i++) {
            if (arguments.charAt(i) != ',' && !secondArgument) {
                argument1.append(arguments.charAt(i));
            } else {
                secondArgument = true;
                if (arguments.charAt(i) != ',') {
                    argument2.append(arguments.charAt(i));
                }
            }
        }
        double arg1;
        double arg2;
        if (!secondArgument) {
            arg1 = switch (argument1.toString()) {
                case "e" -> Math.E;
                case "-e" -> -Math.E;
                case "-pi" -> -Math.PI;
                case "pi" -> Math.PI;
                default -> Double.parseDouble(argument1.toString());
            };
            listResult.add(arg1);
        } else {
            arg1 = switch (argument1.toString()) {
                case "e" -> Math.E;
                case "pi" -> Math.PI;
                case "-e" -> -Math.E;
                case "-pi" -> -Math.PI;
                default -> Double.parseDouble(argument1.toString());
            };
            listResult.add(arg1);

            arg2 = switch (argument2.toString()) {
                case "e" -> Math.E;
                case "pi" -> Math.PI;
                case "-e" -> -Math.E;
                case "-pi" -> -Math.PI;
                default -> Double.parseDouble(argument2.toString());
            };
            listResult.add(arg2);
        }
        return listResult;
    }

    private double getResultFunk(String expression) {
        double result = 0;
        ArrayList<Double> listArguments;
        System.out.println(expression);
        int index = findBracketsOrActionSign(expression);
        String functionType;
        int startPosParameter;
        if (indexPlusMinus) {
            functionType = expression.substring(1, index);
            startPosParameter = 5;
        } else {
            functionType = expression.substring(0, index);
            startPosParameter = 4;
        }
        System.out.println("expression " + functionType);
        switch (functionType) {
            case "pow":
                listArguments = getArguments(expression.substring(startPosParameter, expression.length() - 2));
                result = Math.pow(listArguments.get(0), listArguments.get(1));
                break;
            case "asin":
                listArguments = getArguments(expression.substring(startPosParameter + 1, expression.length() - 2));
                result = Math.asin(listArguments.get(0));
                break;
            case "sin":
                listArguments = getArguments(expression.substring(startPosParameter, expression.length() - 2));
                result = Math.sin(listArguments.get(0));
                break;
            case "acos":
                listArguments = getArguments(expression.substring(startPosParameter + 1, expression.length() - 2));
                result = Math.acos(listArguments.get(0));
                break;
            case "cos":
                listArguments = getArguments(expression.substring(startPosParameter, expression.length() - 2));
                result = Math.cos(listArguments.get(0));
                break;
            case "sqrt":
                listArguments = getArguments(expression.substring(startPosParameter + 1, expression.length() - 2));
                result = Math.sqrt(listArguments.get(0));
                break;
            case "atan":
                listArguments = getArguments(expression.substring(startPosParameter + 1, expression.length() - 2));
                result = Math.atan(listArguments.get(0));
                break;
            case "tan":
                listArguments = getArguments(expression.substring(startPosParameter, expression.length() - 2));
                result = Math.tan(listArguments.get(0));
                break;
            case "ln":
                listArguments = getArguments(expression.substring(startPosParameter - 1, expression.length() - 2));
                result = Math.log(listArguments.get(0));
                break;
            case "lg":
                listArguments = getArguments(expression.substring(startPosParameter - 1, expression.length() - 2));
                result = Math.log10(listArguments.get(0));
                break;
            case "significant":
                listArguments = getArguments(expression.substring(startPosParameter + 8, expression.length() - 2));
                result = 1 / listArguments.get(0);
                break;
        }
        if (indexPlusMinus) {
            return -result;
        }
        return result;
    }

    private void plusAction() {
        int flagAction = checkCorrectEnter();

        System.out.println("Flag:" + flagAction);
        switch (flagAction) {
            case 1:
                secondValue = findSecondNumber();
                System.out.println("plusFirst" + firstValue);
                System.out.println("plusSecond" + secondValue);
                double resultN_N = firstValue + secondValue;
                firstValue = resultN_N;

                Platform.runLater(() -> textArea.setText(resultN_N + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
            case 2:
                String secondValueSTR = findSecondValue();
                secondValue = getResultFunk(secondValueSTR);
                System.out.println(secondValue);
                double resultN_F = firstValue + secondValue;
                firstValue = resultN_F;

                Platform.runLater(() -> textArea.setText(resultN_F + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
            case 3:
                String funkSTR = textArea.getText();
                double result = getResultFunk(funkSTR);
                firstValue = result;

                Platform.runLater(() -> textArea.setText(result + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
            case 4:
                int indexFactorial = findBracketsOrActionSign(textArea.getText());
                Integer value;
                if (indexPlusMinus) {
                    value = Integer.parseInt(textArea.getText(1, indexFactorial));
                    firstValue = -calculateFactorial(value);
                } else {
                    value = Integer.parseInt(textArea.getText(0, indexFactorial));
                    firstValue = calculateFactorial(value);
                }
                System.out.println("value !: " + value);
                //firstValue = calculateFactorial(value);

                Platform.runLater(() -> textArea.setText(firstValue + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
            case 5:
                firstValue = findFirstNumber();
                break;
            case 6:
                String valueFactorial = findSecondValue();

                if (valueFactorial.startsWith("-")) {
                    secondValue = -calculateFactorial(Integer.parseInt(valueFactorial.substring(1, valueFactorial.length() - 2)));
                } else
                    secondValue = calculateFactorial(Integer.parseInt(valueFactorial.substring(0, valueFactorial.length() - 2)));

                double resultN_Factorial = firstValue + secondValue;
                firstValue = resultN_Factorial;

                Platform.runLater(() -> textArea.setText(resultN_Factorial + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
        }
        Platform.runLater(() -> textArea.positionCaret(textArea.getLength()));
    }

    private void minusAction() {
        int flagAction = checkCorrectEnter();

        //System.out.println("Flag:" + flagAction);
        switch (flagAction) {
            case 1:
                secondValue = findSecondNumber();
                double resultN_N = firstValue - secondValue;
                firstValue = resultN_N;

                Platform.runLater(() -> textArea.setText(resultN_N + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
            case 2:
                String secondValueSTR = findSecondValue();
                System.out.println("secondValueSTR: " + secondValueSTR);
                secondValue = getResultFunk(secondValueSTR);
                System.out.println(secondValue);
                double resultN_F = firstValue - secondValue;
                firstValue = resultN_F;

                Platform.runLater(() -> textArea.setText(resultN_F + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
            case 3:
                String funkSTR = textArea.getText();
                double result = getResultFunk(funkSTR);
                firstValue = result;

                Platform.runLater(() -> textArea.setText(result + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
            case 4:
                int indexFactorial = findBracketsOrActionSign(textArea.getText());
                Integer value;
                if (indexPlusMinus) {
                    value = Integer.parseInt(textArea.getText(1, indexFactorial));
                    firstValue = -calculateFactorial(value);
                } else {
                    value = Integer.parseInt(textArea.getText(0, indexFactorial));
                    firstValue = calculateFactorial(value);
                }
                System.out.println("value !: " + value);
                //firstValue = calculateFactorial(value);

                Platform.runLater(() -> textArea.setText(firstValue + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
            case 5:
                firstValue = findFirstNumber();
                System.out.println(firstValue);
                break;
            case 6:
                String valueFactorial = findSecondValue();

                if (valueFactorial.startsWith("-")) {
                    secondValue = -calculateFactorial(Integer.parseInt(valueFactorial.substring(1, valueFactorial.length() - 2)));
                } else
                    secondValue = calculateFactorial(Integer.parseInt(valueFactorial.substring(0, valueFactorial.length() - 2)));

                double resultN_Factorial = firstValue - secondValue;
                firstValue = resultN_Factorial;

                Platform.runLater(() -> textArea.setText(resultN_Factorial + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
        }
        Platform.runLater(() -> textArea.positionCaret(textArea.getLength()));
    }

    private void multiplyAction() {
        int flagAction = checkCorrectEnter();

        //System.out.println("Flag:" + flagAction);
        switch (flagAction) {
            case 1:
                secondValue = findSecondNumber();
                double resultN_N = firstValue * secondValue;
                firstValue = resultN_N;

                Platform.runLater(() -> textArea.setText(resultN_N + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
            case 2:
                String secondValueSTR = findSecondValue();
                System.out.println("secondValueSTR: " + secondValueSTR);
                secondValue = getResultFunk(secondValueSTR);
                System.out.println(secondValue);
                double resultN_F = firstValue * secondValue;
                firstValue = resultN_F;

                Platform.runLater(() -> textArea.setText(resultN_F + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
            case 3:
                String funkSTR = textArea.getText();
                double result = getResultFunk(funkSTR);
                firstValue = result;

                Platform.runLater(() -> textArea.setText(result + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
            case 4:
                int indexFactorial = findBracketsOrActionSign(textArea.getText());
                Integer value;
                if (indexPlusMinus) {
                    value = Integer.parseInt(textArea.getText(1, indexFactorial));
                    firstValue = -calculateFactorial(value);
                } else {
                    value = Integer.parseInt(textArea.getText(0, indexFactorial));
                    firstValue = calculateFactorial(value);
                }
                System.out.println("value !: " + value);
                //firstValue = calculateFactorial(value);

                Platform.runLater(() -> textArea.setText(firstValue + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
            case 5:
                firstValue = findFirstNumber();
                System.out.println(firstValue);
                break;
            case 6:
                String valueFactorial = findSecondValue();

                if (valueFactorial.startsWith("-")) {
                    secondValue = -calculateFactorial(Integer.parseInt(valueFactorial.substring(1, valueFactorial.length() - 2)));
                } else
                    secondValue = calculateFactorial(Integer.parseInt(valueFactorial.substring(0, valueFactorial.length() - 2)));

                double resultN_Factorial = firstValue * secondValue;
                firstValue = resultN_Factorial;

                Platform.runLater(() -> textArea.setText(resultN_Factorial + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
        }
        Platform.runLater(() -> textArea.positionCaret(textArea.getLength()));
    }

    private void devideAction() {
        int flagAction = checkCorrectEnter();

        System.out.println("Flag:" + flagAction);
        switch (flagAction) {
            case 1:
                secondValue = findSecondNumber();
                System.out.println("plusFirst" + firstValue);
                System.out.println("plusSecond" + secondValue);
                if(secondValue == 0)
                {
                    alert();
                } else {
                    double resultN_N = firstValue / secondValue;
                    firstValue = resultN_N;
                }
                Platform.runLater(() -> textArea.setText(firstValue + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
            case 2:
                String secondValueSTR = findSecondValue();
                secondValue = getResultFunk(secondValueSTR);
                System.out.println(secondValue);
                double resultN_F = firstValue / secondValue;
                firstValue = resultN_F;

                Platform.runLater(() -> textArea.setText(resultN_F + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
            case 3:
                String funkSTR = textArea.getText();
                double result = getResultFunk(funkSTR);
                firstValue = result;

                Platform.runLater(() -> textArea.setText(result + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
            case 4:
                int indexFactorial = findBracketsOrActionSign(textArea.getText());
                Integer value;
                if (indexPlusMinus) {
                    value = Integer.parseInt(textArea.getText(1, indexFactorial));
                    firstValue = -calculateFactorial(value);
                } else {
                    value = Integer.parseInt(textArea.getText(0, indexFactorial));
                    firstValue = calculateFactorial(value);
                }
                System.out.println("value !: " + value);
                //firstValue = calculateFactorial(value);

                Platform.runLater(() -> textArea.setText(firstValue + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
            case 5:
                firstValue = findFirstNumber();
                break;
            case 6:
                String valueFactorial = findSecondValue();

                if (valueFactorial.startsWith("-")) {
                    secondValue = -calculateFactorial(Integer.parseInt(valueFactorial.substring(1, valueFactorial.length() - 2)));
                } else
                    secondValue = calculateFactorial(Integer.parseInt(valueFactorial.substring(0, valueFactorial.length() - 2)));

                double resultN_Factorial = firstValue / secondValue;
                firstValue = resultN_Factorial;

                Platform.runLater(() -> textArea.setText(resultN_Factorial + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
        }
        Platform.runLater(() -> textArea.positionCaret(textArea.getLength()));
    }

    private void equallyAction() {
        int flagAction = checkCorrectEnter();

        //System.out.println("Flag:" + flagAction);
        switch (flagAction) {
            case 3:
                String funkSTR = textArea.getText();
                double result = getResultFunk(funkSTR);
                firstValue = result;

                Platform.runLater(() -> textArea.setText(result + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
            case 4:
                int indexFactorial = findBracketsOrActionSign(textArea.getText());
                Integer value;
                if (indexPlusMinus) {
                    value = Integer.parseInt(textArea.getText(1, indexFactorial));
                    firstValue = -calculateFactorial(value);
                } else {
                    value = Integer.parseInt(textArea.getText(0, indexFactorial));
                    firstValue = calculateFactorial(value);
                }
                System.out.println("value !: " + value);

                Platform.runLater(() -> textArea.setText(firstValue + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
            case 5:
                firstValue = findFirstNumber();
                System.out.println(firstValue);
                break;
        }
        Platform.runLater(() -> {
            textArea.setText(String.valueOf(firstValue));
            textArea.positionCaret(textArea.getLength());
        });
    }

    private void roundAction() {
        int flagAction = checkCorrectEnter();

        //System.out.println("Flag:" + flagAction);
        switch (flagAction) {
            case 3:
                String funkSTR = textArea.getText();
                double result = getResultFunk(funkSTR);
                firstValue = Math.round(result);

                Platform.runLater(() -> textArea.setText(result + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
            case 4:
                int indexFactorial = findBracketsOrActionSign(textArea.getText());
                Integer value;
                if (indexPlusMinus) {
                    value = Integer.parseInt(textArea.getText(1, indexFactorial));
                    firstValue = -calculateFactorial(value);
                } else {
                    value = Integer.parseInt(textArea.getText(0, indexFactorial));
                    firstValue = calculateFactorial(value);
                }
                System.out.println("value !: " + value);

                Platform.runLater(() -> textArea.setText(firstValue + textArea.getText(textArea.getLength() - 1, textArea.getLength())));
                break;
            case 5:
                firstValue = Math.round(findFirstNumber());
                System.out.println(firstValue);
                break;
        }
        Platform.runLater(() -> {
            textArea.setText(String.valueOf(Math.round(firstValue)));
            textArea.positionCaret(textArea.getLength());
        });
    }
}
