import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LISHUAI on 2019/3/3.
 */


@Description(name="zodiac",value="_FUNC_(date) - from the input date string"+
        "or separate month and day arguments,return the sign of the zodiac",extended="Example:\n"+
        "> select _FUNC_(date_string) from src;\n"
        +"> select _FUNC_(month,day) from src;"
)
public class UDFZodiacSign extends UDF{
    private SimpleDateFormat df;
    public UDFZodiacSign(){
        df = new SimpleDateFormat("MM-dd-yyyy");
    }
    public String evaluate(Date bday){
        return this.evaluate1(bday.getMonth(),bday.getDay());
    }
    public String evaluate(String bday){
        Date date = null;
        try{
            date = df.parse(bday);
        }catch (Exception e){
            return null;
        }
        return this.evaluate1(date.getMonth()+1,date.getDay());
    }
    public String evaluate1(Integer month,Integer day){
        if(month==1){
            if(day<20){
                return "Capricorn";
            }else{
                return "Aqarius";
            }
        }
        if(month==2){
            if(day<19){
                return "Aqarius";
            }else{
                return "Pisces";
            }
        }
        return null;
    }
}
