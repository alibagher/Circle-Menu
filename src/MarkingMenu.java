// HelloMVC: a simple MVC example
// the model is just a counter
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

import javax.swing.*;
import java.awt.*;
import static java.awt.GraphicsDevice.WindowTranslucency.*;
import java.util.*;
import java.awt.Component;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;


class MarkingMenu extends JPopupMenu{

    Vector<String> MItexts = new Vector<String>();
    int MInum = 0;
    Vector<String> Mtexts = new Vector<String>();
    int Mnum = 0;

    //vector holding all of the JMenus
    Vector<javax.swing.JMenu> MenuVector = new Vector<javax.swing.JMenu>();

    // map holding all of the menuitems of each jmenu
    Map<javax.swing.JMenu,javax.swing.JMenuItem> MenuItemVector = new HashMap<javax.swing.JMenu,javax.swing.JMenuItem>();

    ShapedWindowDemo Gsw;
    //JPopupMenu j;


    //centre point
    Point c = new Point();

    // mouse point
    Point M = new Point();


    private int xx,yy;

    public MarkingMenu(){
        //j = new JPopupMenu();
    }

    //@Override
    public class JMenu extends javax.swing.JMenu {
        javax.swing.JMenu mine;
        public JMenu(String s){
            Mtexts.add(s);
            Mnum++;
            mine = new javax.swing.JMenu(s);
            MenuVector.add(mine);
        }

        public JMenuItem add(JMenuItem menuItem){
            MenuItemVector.put(mine, menuItem.mine);
            return menuItem;
        }
    }

    //@Override
    public class JMenuItem extends javax.swing.JMenuItem {
        javax.swing.JMenuItem mine;
        JMenuItem(){

        }
        public JMenuItem(String s){
            MItexts.add(s);
            MInum++;
            mine = new javax.swing.JMenuItem(s);
        }
    }

    // the second level of the menu
    public class ShapedWindowDemo2 extends JFrame {
        int cent = 150/2;
        int whichMenu;
        public ShapedWindowDemo2(Component invoker, int w, int m) {
            whichMenu = m;
            // got from oracle website
            // It is best practice to set the window's shape in
            // the componentResized method.  Then, if the window
            // changes size, the shape will be correctly recalculated.
            addComponentListener(new ComponentAdapter() {
                // Give the window an elliptical shape.
                // If the window is resized, the shape is recalculated here.
                @Override
                public void componentResized(ComponentEvent e) {
                    setShape(new Ellipse2D.Double(0,0,w,w));
                }
            });

            setUndecorated(true);
            setSize(151,151);
            setLocation(xx+invoker.getLocationOnScreen().x, yy+invoker.getLocationOnScreen().y);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            repaint();

            MarkingMenu.this.repaint();

        }

        //paint the circle and lines.
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;

            g2.setColor(Color.WHITE);
            g2.fillOval(0, 0, 150, 150);
            g2.setColor(Color.BLACK);
            g2.drawOval(0, 0, 150, 150);

            g2.drawLine(cent, cent, 150, 150);
            g2.drawLine(cent, cent, 0, 150);
            g2.drawLine(cent, cent, cent, 0);

            g.setFont(new Font("TimesRoman", Font.BOLD, 14));

            g2.drawString((MenuItemVector.get(MenuVector.get(whichMenu))).getText(), cent-10, cent+40);
            g2.drawString((String)(MenuItemVector.get(MenuVector.get(whichMenu))).getText(), cent+25, cent-20);
            g2.drawString((String)(MenuItemVector.get(MenuVector.get(whichMenu))).getText(), cent-58, cent-20);
            //g2.drawString("hi", cent-58, cent-20);

        }


    }

    public class ShapedWindowDemo extends JFrame {
        int cent = 150/2;
        public ShapedWindowDemo(Component invoker, int w) {
            super("ShapedWindow");
            //setLayout(new GridBagLayout());

            // It is best practice to set the window's shape in
            // the componentResized method.  Then, if the window
            // changes size, the shape will be correctly recalculated.
            addComponentListener(new ComponentAdapter() {
                // Give the window an elliptical shape.
                // If the window is resized, the shape is recalculated here.
                @Override
                public void componentResized(ComponentEvent e) {
                    setShape(new Ellipse2D.Double(0,0,w,w));
                    //repaint();
                }
            });

            setUndecorated(true);
            setSize(151,151);
            setLocation(xx+invoker.getLocationOnScreen().x, yy+invoker.getLocationOnScreen().y);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            MarkingMenu.this.repaint();

            //add(new JButton("I am a Button"));
            this.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    M.x = e.getX();
                    M.y = e.getY();

                    MarkingMenu.this.repaint();

                    int diffX = Math.abs(M.x - cent);
                    int diffY = Math.abs(M.y - cent);
                    double s = (double) (diffX*diffX) + (diffY*diffY);
                    int dist = (int)(Math.sqrt(s));

                    int slope1 = (150/2 - 150) / (150/2 - 150); // centre to bottom right -- positive
                    int slope2 = (150/2 - 150) / (150/2 - 0); // centre to bottom left ----- negative

                    //if this is the one selected, set flag true.
                    if(dist <= 150/2 && M.x > 150/2 && M.y < 150 && (cent-M.y)/(cent-M.x) < slope1){ // radius
                        xx += 75;
                        yy -= 35;
                        ShapedWindowDemo2 sw = new ShapedWindowDemo2(invoker, 150, 1);
                        sw.addFocusListener( new FocusListener() {
                            private boolean gained = false;
                            @Override
                            public void focusGained( FocusEvent e ) {
                                gained = true;
                                sw.repaint();
                            }

                            @Override
                            public void focusLost( FocusEvent e ) {
                                if ( gained ){
                                    sw.setVisible(false);
                                    sw.repaint();
                                    sw.dispose();
                                }
                            }
                        });
                        xx -= 75;
                        yy += 35;
                        sw.setVisible(true);
                        sw.repaint();
                        sw.setVisible(true);
                        repaint();
                    }else if(dist <= 150/2 && M.x < 150/2 && M.y < 150 && (cent-M.y)/(cent-M.x) > slope2){
                        xx -= 75;
                        yy -= 35;
                        ShapedWindowDemo2 sw = new ShapedWindowDemo2(invoker, 150, 2);
                        sw.addFocusListener( new FocusListener() {
                            private boolean gained = false;
                            @Override
                            public void focusGained( FocusEvent e ) {
                                gained = true;
                                sw.repaint();
                            }

                            @Override
                            public void focusLost( FocusEvent e ) {
                                if ( gained ){
                                    sw.setVisible(false);
                                    sw.repaint();
                                    sw.dispose();
                                }
                            }
                        });
                        xx += 75;
                        yy += 35;
                        sw.setVisible(true);
                        sw.repaint();
                        sw.setVisible(true);
                        repaint();
                    }else if(dist <= 150/2){
                        yy += 100;
                        ShapedWindowDemo2 sw = new ShapedWindowDemo2(invoker, 150 , 0);
                        sw.addFocusListener( new FocusListener() {
                            private boolean gained = false;
                            @Override
                            public void focusGained( FocusEvent e ) {
                                gained = true;
                                sw.repaint();
                            }

                            @Override
                            public void focusLost( FocusEvent e ) {
                                if ( gained ){
                                    sw.setVisible(false);
                                    sw.repaint();
                                    sw.dispose();
                                }
                            }
                        });
                        yy -= 100;
                        sw.setVisible(true);
                        sw.repaint();
                        sw.setVisible(true);
                        repaint();
                    }
                    MarkingMenu.this.repaint();



                }
            });
        }



        public void paint(Graphics g) {
            //super.paint(g);
            Graphics2D g2 = (Graphics2D)g;
            g2.setColor(Color.WHITE);
            g2.fillOval(0, 0, 150, 150);
            g2.setColor(Color.BLACK);
            g2.drawOval(0, 0, 150, 150);
            //MarkingMenu.this.repaint();



            g2.drawLine(cent, cent, 150, 150);
            g2.drawLine(cent, cent, 0, 150);
            g2.drawLine(cent, cent, cent, 0);

            g.setFont(new Font("TimesRoman", Font.BOLD, 14));

            g2.drawString((String)MenuVector.get(0).getText(), cent-10, cent+40);
            g2.drawString((String)MenuVector.get(1).getText(), cent+25, cent-20);
            g2.drawString((String)MenuVector.get(2).getText(), cent-58, cent-20);

        }


    }



    @Override
    public void show(Component invoker, int x, int y) {

        x = x - 20;
        y = y - 20;
        xx=x;
        yy=y;

        c.x = x+(150/2);
        c.y = y+(150/2);

        // Determine what the GraphicsDevice can support.
        GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        final boolean isTranslucencySupported =
                gd.isWindowTranslucencySupported(TRANSLUCENT);

        //If shaped windows aren't supported, exit.
        if (!gd.isWindowTranslucencySupported(PERPIXEL_TRANSPARENT)) {
            System.err.println("Shaped windows are not supported");
            System.exit(0);
        }

        //If translucent windows aren't supported,
        //create an opaque window.
        if (!isTranslucencySupported) {
            System.out.println(
                    "Translucency is not supported, creating an opaque window");
        }

        // Create the GUI on the event-dispatching thread
        SwingUtilities.invokeLater(()-> {
            ShapedWindowDemo sw = new ShapedWindowDemo(invoker, 150);
            sw.repaint();

            sw.addFocusListener( new FocusListener() {
                private boolean gained = false;
                @Override
                public void focusGained( FocusEvent e ) {
                    gained = true;
                    sw.repaint();
                }

                @Override
                public void focusLost( FocusEvent e ) {
                    if ( gained ){
                        //sw.setVisible(false);
                        //sw.repaint();
                        //sw.dispose();
                    }
                }
            });
            sw.setVisible(true);
        });
    }
}
