import java.io.File
import java.io.PrintWriter
//import java.sql.DriverManager.println
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import java.util.Objects.toString
import kotlin.collections.LinkedHashMap

fun main(args : Array<String>) {
    //var EventArray = emptyArray<String>()
    //var EventMap = mutableMapOf<String, String>()
    //ReadFileToMap(EventMap)
    var EventMap = ReadFileToMap()

    var I = 0
    print("Please input one of the following commands \n")
    print("? -> see all possible commands \n")
    print("Set -> Set a New Event \n")
    print("Date -> Check current Date \n")
    print("Check -> See how many events are Today \n")
    print("All -> See all Scheduled Events \n")
    print("Save -> Save Changes to Scheduled Events \n")
    print("Exit -> To close program \n")


    // Loop that acts as the Main Controller
    while (I <= 0 ){
        print("Please Input your Command: ")
        var ControlInput = readLine()


        when(ControlInput) {
            "?" -> CommandsList()
            "Set" -> SetEvent(EventMap)
            "Date" -> println(toString(GetCurrentDate()))
            "Check" -> EventCheck(GetCurrentDate(), EventMap)
            "All" -> GetScheduled(EventMap)
            "Save" -> WriteMapToFile(EventMap)
            "Exit" -> I = 5
            else -> println(ControlInput + " is not a valid command. \n")
        }
    }
    print("Thank you, Good-Bye \n")
}

/*
Prints list of all commands
 */
fun CommandsList(){
    print("The following is a list of all possible commands \n")
    print("? -> see all possible commands \n")
    print("Set -> Set a New Event\n")
    print("Date -> Check current Date \n")
    print("Check -> See how many events are Today \n")
    print("All -> See all Scheduled Events \n")
    print("Save -> Save Changes to Scheduled Events \n")
    print("Exit -> To close program \n")
}

/*
Reads the data from ScheduledEvents.txt File into the EventMap
 */
fun ReadFileToMap() : MutableMap<String, String>{
    val EventMap = mutableMapOf<String, String>()

    var i = 0
    File("ScheduledEvents.txt").forEachLine{
        var TheLine = it
        var Items = TheLine.split(":")
        var TheKey = Items[0]
        var TheVal = Items[1]
        EventMap.put(TheKey,TheVal)  //TODO Cannot Add multiple events with same key
        i += 1
    }
    return EventMap
}

/*
Saves the data from the EventMap into the ScheduledEvents.txt file
 */
fun WriteMapToFile(EventMap: MutableMap<String, String>){
    
    // Delete Files Previous Content
    val PW = PrintWriter("ScheduledEvents.txt");
    PW.print("");
    PW.close();
    var TextBlock : String = ""
    EventMap.forEach {
        var TextString = it.key + ":" + it.value + "\n"
        //TextBlock.plus(it.key + ":" + it.value + "\n")
        TextBlock= TextBlock.plus(TextString) // creates a textBlock of the value in the Map
        //File("ScheduledEvents.txt").writeText(it.key + ":" + it.value + "\n")
    }
    File("ScheduledEvents.txt").writeText(TextBlock)
}

/*
Create and saves a new event into the EventMap
 */
fun SetEvent(EventMap: MutableMap<String, String>): MutableMap<String, String>{
    print("Enter Date of Event: ")          //TODO Research DatePicker Further
    val DateInput = readLine()!!    // Get User input on When event is scheduled

    print("Enter Name of Event: ")
    val EventName = readLine()!!    // Get User input on what new event is  

   // var Format_Date = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()) //create a format to check against DateInput
   //val Date_Format = Format_Date.parse(DateInput)

    EventMap.put( EventName, DateInput) // put the name and date of the new event into the map

    return EventMap
}

/*
Checks for the Events Scheduled for Today
 */
fun EventCheck( Current : String, EventMap: MutableMap<String, String>){
    var Event_Num = 0 
    EventMap.forEach {
         if(it.value == Current){
            print(it.key + " is Scheduled Today \n")
           Event_Num += 1 
         }
    }
    print("in Total Today You have " + Event_Num + " Events Scheduled \n")
}

/*
Prints out a list of all scheduled Events 
 */
fun GetScheduled(EventMap: MutableMap<String, String>){
        EventMap.forEach {
            print(it.key + " is Scheduled on " + it.value + "\n")
        }
}

/*
Returns the Current Date
 */
fun GetCurrentDate(): String{
    val current = LocalDate.now()
    return toString(current)

}