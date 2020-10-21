 /*
       A trivial applet that tests the StopWatchTimer component.
       The applet just creates and shows a StopWatchTimer.
    */
	package webserver;

    import java.awt.*;
    import java.applet.*;
    
    public class TestStopWatchRunner extends Applet {
    
       public void init() {
          
          StopWatchRunner watch = new StopWatchRunner();
          watch.setFont( new Font("SansSerif", Font.BOLD, 24) );
          watch.setBackground(Color.white);
          watch.setForeground( new Color(180,0,0) );
          setBackground(Color.white);
          setLayout(new BorderLayout() );
          add(watch, BorderLayout.CENTER);
          
       }
    
    }