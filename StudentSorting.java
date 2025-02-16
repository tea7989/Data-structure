import java.util.*;

public class StudentSorting {
    public static void main(String[] args) { 
        System.out.println("The max heap is like");
        PriorityQueue<Student> maxHeap = new PriorityQueue<>((a, b) -> b.grade -a.grade); 
        // Max-heap (descending order)
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
    }
}
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