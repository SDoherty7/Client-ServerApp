
package embedded;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class DateTimeService implements Serializable
{
   private Calendar calendar;
   private int temp;
   private String date;

   public DateTimeService()
   {
   }
   
   public DateTimeService(String date, int temp)
   {
	 this.date = date;
	 this.temp = temp;
   }

   //method returns date/time as a formatted String object
   public String getDateAndTime()
   {
	  this.calendar = Calendar.getInstance();
	  Date d = this.calendar.getTime();
	  return "The BeagleBone time is: " + d.toString();	
   }
   
   //Method to get temperature readings, values are made up for the sake of demonstration
   public int getTempReadings()
   {
	   int max = 22;
	   int min = 18;
	   return (int)(Math.random() * ((max - min) + 1)) + min;
   }

   //Getter allows us to keep members private
   public int getTemp() {return this.temp;}

   public String getDate() {return this.date;}

}

/*   
 * Sample method for getting the cpu temperature of a Raspberry Pi
 * For my application I created random temperature values for testing purposes

public String getPiTemp(){                 
	   temperature = null;
	   String fileName = "/sys/class/thermal/thermal_zone0/temp";
	   try {
        FileReader fileReader = new FileReader(fileName);

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        while((temperature = bufferedReader.readLine()) != null) {
            return this.temperature;
        }
        
        bufferedReader.close();
    }
    catch(FileNotFoundException ex) {
        System.out.println("Unable to open file '" + fileName + "'");
    }
    catch(IOException ex) {
        System.out.println("Error reading file '" + fileName + "'");
    }
	return this.temperature;
	}
*/