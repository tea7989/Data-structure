import java.util.*;

class Student {
    int studentId;
    int grade;
 
    public Student(int studentId, int grade) {
        this.studentId = studentId;
        this.grade = grade;
    }
    @Override
    public String toString() {
        return "Student ID: " + studentId + ", Grade: " + grade;
    }
}

public class InternshipSelection {
    public static void main(String[] args) { 
        PriorityQueue<Student> maxHeap = new PriorityQueue<>((a, b) -> a.grade - b.grade);  
 
        maxHeap.offer(new Student(1, 85));
        maxHeap.offer(new Student(2, 90));
        maxHeap.offer(new Student(3, 80));
        maxHeap.offer(new Student(4, 95));
        maxHeap.offer(new Student(5, 70));
        List<Student> sortedStudents = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            sortedStudents.add(maxHeap.poll());  
        }
 
        Collections.reverse(sortedStudents);
 
        System.out.println("Students sorted by grades in ascending order:");
        for (Student student : sortedStudents) {
            System.out.println(student);
        }
 
        Student nextTopper = maxHeap.poll();  
        System.out.println("Next topper for internship: " + nextTopper);
    }
}
/* public class Student{
String studentId; int marks;
public Student(String studentId, int marks){
this.stdentIstudentID; this.marks=marks;}
@Override
public string toString(){
return "StudentId"+stdentId+"marks"+marks;}}

public static void main(String args[]){
PriorityQueue<Strundets> maxHeap=new PriorityQueue<>((a,b)->a.marks-b.marks); 

}
 * 
 */