package librarymgmtsystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.beans.value.*;
import javafx.event.ActionEvent;
import javafx.collections.*;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.*;
import java.lang.String.*;

public class LibraryMgmtSystem extends Application {

    String newDate = "";

    @Override
    public void start(Stage primaryStage) {

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/login", "rithika", "rithika");
            System.out.println("connected");
            Statement stmt = con.createStatement();

            String sql = "INSERT INTO books VALUES ('AB12')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO books VALUES ('CD34')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO books VALUES ('EF56')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO books VALUES ('GH78')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO books VALUES ('IJ910')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO logindemo VALUES ('18ucsa101','20/10/2000')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO logindemo VALUES ('18ucsa102','12/10/2000')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO logindemo VALUES ('18ucsa103','13/10/2000')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO logindemo VALUES ('18ucsa104','14/10/2000')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO logindemo VALUES ('18ucsa105','15/10/2000')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO logindemo VALUES ('18ucsa106','16/10/2000')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO logindemo VALUES ('18ucsa107','17/10/2000')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO logindemo VALUES ('19pcsa101','11/11/2000')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO logindemo VALUES ('19pcsa102','12/11/2000')";
        } catch (Exception e) {
            System.out.println(e);
        }

        Scene scene1, scene2;

        //scene 1
        GridPane root = new GridPane();
        scene1 = new Scene(root, 700, 600);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));
        root.setAlignment(Pos.CENTER);
        Rectangle r1 = new Rectangle();
        r1.setWidth(210);
        r1.setHeight(29);
        r1.setFill(Color.BURLYWOOD);
        root.add(r1, 0, 0, 2, 1);
        Text title = new Text("   USER LOGIN ");
        title.getStyleClass().add("fancytext");
        title.setFill(Color.WHITE);
        root.add(title, 0, 0, 2, 1);
        Label username = new Label("USER NAME:");
        username.getStyleClass().add("fantext");
        root.add(username, 0, 1);
        Label password = new Label("PASSWORD:");
        root.add(password, 0, 3);
        password.getStyleClass().add("fantext");

        TextField usertext = new TextField();
        root.add(usertext, 0, 2);
        usertext.setPromptText("Enter username");

        PasswordField passtext = new PasswordField();
        root.add(passtext, 0, 4);
        passtext.setPromptText("Enter password");

        Button btn = new Button("SIGN IN");
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(btn);
        root.add(hbox, 0, 5);
        btn.setStyle("-fx-background-color:burlywood;-fx-border-width:3;-fx-font-size:18;");

        Rectangle rec2 = new Rectangle();
        rec2.setHeight(20);
        rec2.setWidth(255);
        rec2.setFill(Color.BURLYWOOD);
        Text outputtext = new Text();

        //SCENE 2
        VBox root1 = new VBox(20);
        root1.setAlignment(Pos.CENTER);
        scene2 = new Scene(root1, 700, 600);

        Label smsg = new Label();
        smsg.setStyle("-fx-background-color:burlywood;");
        ToggleGroup group = new ToggleGroup();
        ToggleButton newsb = new ToggleButton("Latest News");
        newsb.setStyle("-fx-background-color:burlywood;-fx-font-size:18;");
        newsb.setToggleGroup(group);
        ScrollPane sp = new ScrollPane();
        VBox subsp = new VBox();
        Label spl1 = new Label("Announcement:");
        spl1.setStyle("-fx-text-fill:burlywood;");
        Label spl2 = new Label("\nRegistration for the Annual Book Fair is now open.\nContact the Librarian for more details.\n\n");
        spl2.setStyle("-fx-font-size:17; -fx-font-weight:normal;-fx-text-fill:white;");
        Label spl3 = new Label("New Books:\n\n\n");
        spl3.setStyle("-fx-text-fill:burlywood;");
        ObservableList<String> names = FXCollections.observableArrayList("The Night Circus", "All the Bright Places", "The Alchemist", "Pride and Prejudice", "1984", "Oliver Twist", "Murder on the Orient Express", "The Secret Garden", "Anne of Green Gables");
        ListView<String> listView = new ListView<>(names);
        listView.setMaxSize(190, 160);
        subsp.getChildren().addAll(spl1, spl2, spl3, listView);
        subsp.setStyle("-fx-background-color:black;");
        subsp.setSpacing(-30);
        sp.setStyle("-fx-background-color:burlywood;");
        sp.setContent(subsp);
        sp.setMaxSize(390, 200);
        sp.setVisible(false);

        //event handling for news button
        group.selectedToggleProperty().addListener(
                new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                if (newsb.isSelected() == true) {
                    sp.setVisible(true);
                    newsb.setText("Hide News");
                } else if (newsb.isSelected() == false) {
                    sp.setVisible(false);
                    newsb.setText("Latest News");
                }
            }
        });

        Image bimg = new Image(getClass().getResourceAsStream("borrow.png"));
        ImageView bview = new ImageView(bimg);
        Image rimg = new Image(getClass().getResourceAsStream("return.png"));
        ImageView rview = new ImageView(rimg);
        bview.setFitHeight(60);
        rview.setFitHeight(60);
        bview.setPreserveRatio(true);
        rview.setPreserveRatio(true);

        Button borrowBook = new Button("Borrow A Book");
        borrowBook.setStyle("-fx-background-color:burlywood;-fx-font-size:18;");
        Button returnBook = new Button("Return A Book");
        returnBook.setStyle("-fx-background-color:burlywood;-fx-font-size:18;");

        borrowBook.setGraphic(bview);
        returnBook.setGraphic(rview);

        root1.getChildren().addAll(smsg, newsb, sp, borrowBook, returnBook);

        //BORROW SCENE
        GridPane bRoot = new GridPane();
        bRoot.setHgap(10);
        bRoot.setVgap(10);
        bRoot.setAlignment(Pos.CENTER);

        Label bl1 = new Label("BOOK ID:");
        bl1.getStyleClass().add("fantext");
        bRoot.add(bl1, 0, 1);

        TextField borrowBookID = new TextField();
        borrowBookID.setPrefWidth(150);
        borrowBookID.setPromptText("Enter Book ID");
        bRoot.add(borrowBookID, 1, 1);

        Label bl2 = new Label("DATE:");
        bl2.getStyleClass().add("fantext");
        bRoot.add(bl2, 0, 2);

        DatePicker date1 = new DatePicker();
        date1.setValue(LocalDate.now());
        date1.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0 || date.compareTo(today) > 0);
            }
        });
        bRoot.add(date1, 1, 2);

        CheckBox ck1 = new CheckBox("Books borrowed on a particular day will \nnot be accepted for return on the same day");
        CheckBox ck2 = new CheckBox("Readers shall not write upon, damage, or \nmake any mark on any book");
        CheckBox ck3 = new CheckBox("Any damage to the book or late return \nmay result in fine payment");
        bRoot.add(ck1, 0, 3, 5, 1);
        bRoot.add(ck2, 0, 4, 5, 1);
        bRoot.add(ck3, 0, 5, 5, 1);
        ck1.getStyleClass().add("cktext");
        ck2.getStyleClass().add("cktext");
        ck3.getStyleClass().add("cktext");
        RadioButton ug = new RadioButton("UG");
        RadioButton pg = new RadioButton("PG");
        ToggleGroup tg = new ToggleGroup();
        ug.setToggleGroup(tg);
        pg.setToggleGroup(tg);
        bRoot.add(ug, 0, 6);
        bRoot.add(pg, 1, 6);
        ug.getStyleClass().add("fantext");
        pg.getStyleClass().add("fantext");
        Button bAct = new Button("BORROW");
        bAct.setStyle("-fx-background-color:burlywood;-fx-font-size:18;");
        bRoot.add(bAct, 0, 8, 2, 1);
        Label bMsg = new Label();
        bRoot.add(bMsg, 0, 9, 7, 1);
        bMsg.setStyle("-fx-background-color:burlywood;");

        HBox b1 = new HBox(20);
        Button bOut = new Button("LOG OUT");
        Button back = new Button("BACK");
        back.setStyle("-fx-background-color:burlywood;-fx-font-size:18;");
        bOut.setStyle("-fx-background-color:burlywood;-fx-font-size:18;");
        b1.getChildren().addAll(bOut, back);
        bRoot.add(b1, 0, 10, 4, 1);

        Scene borrowScene = new Scene(bRoot, 700, 600);

        //event handling for borrow scene ug and pg
        Label note = new Label();
        bRoot.add(note, 0, 7, 10, 1);

        ug.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                LocalDate datenow = date1.getValue();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(sdf.parse(datenow.toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                c.add(Calendar.DAY_OF_MONTH, 7);
                newDate = sdf.format(c.getTime());
                note.setText("UG students must return the book\n by " + newDate);
                note.setStyle("-fx-background-color:burlywood;");
            }
        });

        pg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                LocalDate datenow = date1.getValue();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(sdf.parse(datenow.toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                c.add(Calendar.DAY_OF_MONTH, 14);
                newDate = sdf.format(c.getTime());
                note.setText("PG students must return the book\n by " + newDate);
                note.setStyle("-fx-background-color:burlywood;");
            }
        });

        //RETURN SCENE
        GridPane rRoot = new GridPane();
        rRoot.setHgap(10);
        rRoot.setVgap(10);
        rRoot.setAlignment(Pos.CENTER);

        Label rl1 = new Label("BOOK ID:");
        rl1.getStyleClass().add("fantext");
        rRoot.add(rl1, 0, 1);

        TextField returnBookID = new TextField();
        returnBookID.setPrefWidth(150);
        returnBookID.setPromptText("Enter Book ID");
        rRoot.add(returnBookID, 1, 1);

        Label rl2 = new Label("BORROWED DATE:");
        rl2.getStyleClass().add("fantext");
        rRoot.add(rl2, 0, 2);

        DatePicker date2 = new DatePicker();
        //date2.setValue(LocalDate.now());
        date2.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) > 0);
            }
        });
        rRoot.add(date2, 1, 2);

        Label r13 = new Label("TO BE RETURNED WITHIN:");
        r13.getStyleClass().add("fantext");
        r13.setVisible(false);
        rRoot.add(r13, 0, 3);

        Label tbr = new Label();
        tbr.getStyleClass().add("fantext");
        rRoot.add(tbr, 1, 3);

        Label r14 = new Label("FINE AMOUNT:");
        r14.getStyleClass().add("fantext");
        r14.setVisible(false);
        rRoot.add(r14, 0, 4);

        Label fineamt = new Label();
        fineamt.getStyleClass().add("fantext");
        rRoot.add(fineamt, 1, 4);

        Button rAct = new Button("RETURN");
        rAct.setStyle("-fx-background-color:burlywood;-fx-font-size:18;");
        rRoot.add(rAct, 0, 8, 2, 1);

        Label rMsg = new Label();
        rMsg.setStyle("-fx-background-color:burlywood;");
        rRoot.add(rMsg, 0, 9, 7, 1);

        HBox b2 = new HBox(20);
        Button rOut = new Button("LOG OUT");
        Button rback = new Button("BACK");
        rOut.setStyle("-fx-background-color:burlywood;-fx-font-size:18;");
        rback.setStyle("-fx-background-color:burlywood;-fx-font-size:18;");
        b2.getChildren().addAll(rOut, rback);
        rRoot.add(b2, 0, 10, 4, 1);
        Scene returnScene = new Scene(rRoot, 700, 600);

        rback.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene2);
                returnBookID.setText("");
                r13.setVisible(false);
                r14.setVisible(false);
                tbr.setText("");
                fineamt.setText("");
                date2.setValue(null);
                rMsg.setText("");
            }
        });

        //event handling for log in button in scene 1
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                    Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/login", "rithika", "rithika");
                    PreparedStatement ps = con.prepareStatement("SELECT * FROM logindemo where username=? AND password=?");
                    ps.setString(1, usertext.getText());
                    ps.setString(2, passtext.getText());
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        smsg.setText("Welcome " + usertext.getText() + "!");
                        primaryStage.setScene(scene2);
                    } else {
                        outputtext.setText("   Invalid username or password!");
                        root.add(rec2, 0, 8, 2, 1);
                        root.add(outputtext, 0, 8, 2, 1);
                        outputtext.setStyle("-fx-font-size:16px;-fx-font-weight: bold;");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
//event handling for back button - borrow
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                smsg.setText("Welcome " + usertext.getText() + "!");
                primaryStage.setScene(scene2);

                borrowBookID.setText("");
                date1.setValue(LocalDate.now());
                ck1.setSelected(false);
                ck2.setSelected(false);
                ck3.setSelected(false);
                ug.setSelected(false);
                pg.setSelected(false);
                sp.setVisible(false);
                bMsg.setText("");
                note.setText("");
            }
        });

        //event handling for borrow button in scene 2
        borrowBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(borrowScene);
            }
        });

        //event handling for return button in scene 2
        returnBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(returnScene);
            }
        });

        //event handling for final borrow button
        bAct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String key = usertext.getText();

                if ((!borrowBookID.getText().isEmpty()) && (!(tg.getSelectedToggle() == null)) && ck1.isSelected() && ck2.isSelected() && ck3.isSelected()) {

                    if (((ug.isSelected()) && (key.charAt(2) == 'u')) || ((pg.isSelected()) && (key.charAt(2) == 'p'))) {
                        try {
                            Class.forName("org.apache.derby.jdbc.ClientDriver");
                            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/login", "rithika", "rithika");
                            System.out.println("connected");
                            PreparedStatement ps = con.prepareStatement("SELECT * FROM books WHERE bookid = ?");
                            ps.setString(1, borrowBookID.getText());
                            ResultSet rs = ps.executeQuery();
                            if (rs.next()) {
                                PreparedStatement ps1 = con.prepareStatement("SELECT * FROM borrow WHERE bookid=?");
                                ps1.setString(1, borrowBookID.getText());
                                ResultSet rs1 = ps1.executeQuery();
                                if (rs1.next()) {
                                    bMsg.setText("This book is currently borrowed. Please try again later.");
                                } else {
                                    java.sql.Date bdate = java.sql.Date.valueOf(LocalDate.now());
                                    SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
                                    java.util.Date datee = sdf1.parse(newDate);
                                    java.sql.Date rdate = new java.sql.Date(datee.getTime());
                                    PreparedStatement ps2 = con.prepareStatement("INSERT INTO borrow VALUES (?,?,?,?)");
                                    ps2.setString(1, usertext.getText());
                                    ps2.setString(2, borrowBookID.getText());
                                    ps2.setDate(3, bdate);
                                    ps2.setDate(4, rdate);
                                    int r = ps2.executeUpdate();
                                    System.out.println(r + " row inserted into borrow");
                                    bMsg.setText(" Borrowed successfully! Happy Reading!");

                                    PreparedStatement ps3 = con.prepareStatement("INSERT INTO return VALUES (?,?,?,?,?,?)");
                                    ps3.setString(1, usertext.getText());
                                    ps3.setString(2, borrowBookID.getText());
                                    ps3.setDate(3, bdate);
                                    ps3.setDate(4, rdate);
                                    ps3.setDate(5, null);
                                    ps3.setInt(6, 0);
                                    int r1 = ps3.executeUpdate();
                                    System.out.println(r1 + " rows inserted into return");
                                }
                            } else {
                                bMsg.setText("Invalid Book ID");
                            }

                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    } else {
                        bMsg.setText("Please choose UG or PG correctly.");
                    }
                } else {
                    bMsg.setText("Please fill in the details!");
                }
            }
        });

        //event handling for final return button
        rAct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int fine;
                if ((!(returnBookID.getText().isEmpty())) && (date2.getValue() != null)) {
                    try {
                        Class.forName("org.apache.derby.jdbc.ClientDriver");
                        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/login", "rithika", "rithika");
                        System.out.println("connected");

                        LocalDate sampdate = date2.getValue();
                        System.out.println(sampdate);
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date datee = sdf2.parse(sampdate.toString());
                        java.sql.Date bordate = new java.sql.Date(datee.getTime());
                        System.out.println(bordate);
                        PreparedStatement ps = con.prepareStatement("SELECT * FROM return WHERE deptno = ? AND bookid = ? AND borrowdate = ?");
                        ps.setString(1, usertext.getText());
                        ps.setString(2, returnBookID.getText());
                        ps.setDate(3, bordate);
                        ResultSet rs = ps.executeQuery();

                        if (rs.next()) {
                            java.sql.Date _tbrd = rs.getDate("tbreturndate");
                            java.util.Date tbrd = new java.util.Date(_tbrd.getTime());
                            java.util.Date rtrnd = sdf2.parse(LocalDate.now().toString());
                            java.sql.Date retdate = new java.sql.Date(rtrnd.getTime());
                            if (rtrnd.compareTo(tbrd) <= 0) {
                                fine = 0;
                                rMsg.setText("Returned Successfully! Keep Reading!");
                            } else {
                                fine = 300;
                                rMsg.setText("Returned Successfully! Kindly pay the fine amount at the librarian's desk.");
                            }
                            r13.setVisible(true);
                            tbr.setText(tbrd.toString());
                            r14.setVisible(true);
                            fineamt.setText("Rs. " + fine);

                            PreparedStatement ps1 = con.prepareStatement("UPDATE return SET returneddate = ?, fine = ? WHERE deptno = ? AND bookid = ? AND borrowdate = ?");
                            ps1.setDate(1, retdate);
                            ps1.setInt(2, fine);
                            ps1.setString(3, usertext.getText());
                            ps1.setString(4, returnBookID.getText());
                            ps1.setDate(5, bordate);
                            int r = ps1.executeUpdate();
                            System.out.println(r + " row updated");

                            PreparedStatement ps2 = con.prepareStatement("DELETE FROM borrow WHERE deptno = ? AND bookid = ? AND borrowdate = ?");
                            ps2.setString(1, usertext.getText());
                            ps2.setString(2, returnBookID.getText());
                            ps2.setDate(3, bordate);
                            int rr = ps2.executeUpdate();
                            System.out.println(rr + " row deleted.");
                        } else {
                            rMsg.setText("Invalid Book ID for return");
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else {
                    rMsg.setText("Please fill in the details!");
                }
            }
        });

        // event handling for log out button in borrow scene
        bOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newsb.setText("Latest News");
                borrowBookID.setText("");
                date1.setValue(LocalDate.now());
                ck1.setSelected(false);
                ck2.setSelected(false);
                ck3.setSelected(false);
                ug.setSelected(false);
                pg.setSelected(false);
                sp.setVisible(false);
                note.setText("");
                bMsg.setText("");
                smsg.setText("");
                usertext.setText("");
                passtext.setText("");
                root.getChildren().removeAll(outputtext, rec2);
                primaryStage.setScene(scene1);
            }

        });

        //event handling for log out button in return scene
        rOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                returnBookID.setText("");
                usertext.setText("");
                passtext.setText("");
                r13.setVisible(false);
                r14.setVisible(false);
                tbr.setText("");
                fineamt.setText("");
                date2.setValue(null);
                rMsg.setText("");
                root.getChildren().removeAll(outputtext, rec2);
                primaryStage.setScene(scene1);
            }
        });

        scene1.getStylesheets().add(LibraryMgmtSystem.class.getResource("LibraryCSS.css").toExternalForm());
        scene2.getStylesheets().add(LibraryMgmtSystem.class.getResource("LibraryCSS.css").toExternalForm());
        borrowScene.getStylesheets().add(LibraryMgmtSystem.class.getResource("LibraryCSS.css").toExternalForm());
        returnScene.getStylesheets().add(LibraryMgmtSystem.class.getResource("LibraryCSS.css").toExternalForm());

        primaryStage.setTitle("LIBRARY MANAGEMENT SYSTEM");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
