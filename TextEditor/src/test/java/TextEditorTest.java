import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TextEditorTest{

    TextEditor txteditor;

    @Before
    public void setUp(){
        txteditor = new TextEditor();
    }

    @Test
    public void kirjoita_teksti(){ //testaa tekstin kirjoittamista tekstikenttään
        txteditor.area.append("testi");
        assertTrue((txteditor.area.getText()).equals("testi"));
    }


}
