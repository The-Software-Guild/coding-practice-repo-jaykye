import userio.UserIOImpl;

import java.util.*;

public class StudentQuizGrades {
    private UserIOImpl userIO = new UserIOImpl();
    private Map<String, List<Integer>> studentMap = new HashMap<>();

    public Set<String> viewStudents(){
        return studentMap.keySet();
    }

    public void addStudent(){
        String studentName = userIO.readString("Enter student name you want to add.");
        boolean abort = false;
        for (String k: studentMap.keySet()){
            if (Objects.equals(k, studentName)){
                userIO.print("Student already exist. Aborting.");
                abort = true;
                break;
            }
        }
        if (!abort){
            List<Integer> emptyList = new ArrayList<>();
            studentMap.put(studentName, emptyList);
        }
    }

    public void removeStudent(){
        String studentName = userIO.readString("Enter student name you want to remove.");
        boolean abort = true;

        for (String k: studentMap.keySet()){
            if (Objects.equals(k, studentName)){
                abort = false;
            }
        }
        if (!abort){
            studentMap.remove(studentName);
        }
        else {
            userIO.print("Student does not exist. Aborting.");
        }
    }

    public List viewQuizScores(){
        String studentName = userIO.readString("Whose scores do you want to know?");
        return studentMap.get(studentName);
    }

    public double calculateAverageScore(){
        String studentName = userIO.readString("Enter student name whose average you want to calculate.");
        double sum = 0;
        int numGrades = 0;
        numGrades = studentMap.get(studentName).size();
        for (int grade: studentMap.get(studentName)){
            sum += grade;
        }
        return sum / numGrades;
    }

    public void addGrade(){
        String studentName = userIO.readString("Whose grade do you want to add?");
        List<Integer> currentGrades = studentMap.get(studentName);
        currentGrades.add(userIO.readInt("Enter a grade."));
    }


    public static void main(String[] args) {
        // Use here to test the app.
        StudentQuizGrades test = new StudentQuizGrades();
        test.addStudent();
        System.out.println(test.viewStudents());
        test.addGrade();
        System.out.println(test.viewQuizScores());
    }
}
