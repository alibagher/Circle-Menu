import java.util.Observable;

// HelloMVC: a simple MVC example
// the model is just a counter
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

public class Model extends Observable  {

    private String sText;

    static String STARTING_STRING = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
            "empor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
            "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in " +
            "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non " +
            "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n\n" +
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
            "empor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
            "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in " +
            "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non " +
            "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n\n" +
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
            "empor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
            "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in " +
            "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non " +
            "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n\n" +
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
            "empor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
            "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in " +
            "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non " +
            "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n\n";

    Model() {
        sText = STARTING_STRING;
        setChanged();
    }



    public void doNew() {
        sText = STARTING_STRING;
        setChanged();
        notifyObservers();
    }

    public void setNew(String s){
        sText = s;
        setChanged();
        notifyObservers();
    }





    public String getText(){
        return sText;
    }

}