import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class DailyGoalReminder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> goals = new ArrayList<>();
        LocalTime reminderTime = null;

        System.out.println("🎯  Welcome to Daily Goal Reminder App!");

        try {
          
            System.out.print("Enter the number of goals you want to set for today: ");
            int numGoals = Integer.parseInt(scanner.nextLine());

            if (numGoals <= 0) {
                System.out.println("⚠ You must enter at least one goal.");
                return;
            }

            
            for (int i = 0; i < numGoals; i++) {
                System.out.print("Enter goal " + (i + 1) + ": ");
                String goal = scanner.nextLine().trim();
                if (!goal.isEmpty()) {
                    goals.add(goal);
                } else
                  {
                    System.out.println("⚠ Empty goal ignored.");
                }
            }

            if (goals.isEmpty()) {
                System.out.println("⚠ No valid goals entered. Exiting.");
                return;
            }

         
            System.out.print("Enter reminder time in HH:mm format: ");
            String timeInput = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            reminderTime = LocalTime.parse(timeInput, formatter);
            LocalTime now = LocalTime.now();
            long delay = Duration.between(now, reminderTime).toMillis();

            if (delay < 0) 
           {
                delay += Duration.ofDays(1).toMillis();
            }
            Timer timer = new Timer();
            TimerTask reminderTask = new TimerTask()
               {
               
                public void run() {
                    System.out.println("\n🔔 Reminder! Here are your goals for today:");
                    for (int i = 0; i < goals.size(); i++) {
                        System.out.println((i + 1) + ". " + goals.get(i));
                    }
                }
            };

            System.out.println("⏰ Reminder set for " + reminderTime + ". You will be reminded daily.");
            timer.scheduleAtFixedRate(reminderTask, delay, Duration.ofDays(1).toMillis());

        } 
            catch (Exception e)
          {
            System.out.println("❌ Error: Invalid input. " + e.getMessage());
        } 
            finally 
          {
            scanner.close();
        }
    }
}