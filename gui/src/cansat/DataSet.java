package cansat;

import java.util.ArrayList;
import java.util.List;

public class DataSet
{
    public static List<DataSet> dataSets;
    public String header;
    public String body;
    public String footer;
    
    static {
        DataSet.dataSets = new ArrayList<DataSet>();
    }
    
    public DataSet(final String Header, final String Body, final String Footer) {
        this.header = Header;
        this.body = Body;
        this.footer = Footer;
        DataSet.dataSets.add(this);
    }
}