package com.shelja;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        instructor s1 = new instructor();
        common s2 = new common();
        int n1 = 0;
        String id = "";
        ArrayList<String> naam = new ArrayList<>();
        while (true){
            System.out.println("Welcome to backpack");
            System.out.println("1. Enter as instructor");
            System.out.println("2. Enter as student");
            System.out.println("3. Exit");
            n1 = sc.nextInt();
            if (n1==3)
                break;
            else if (n1==1) {
                System.out.println("Instructors:");
                System.out.println("0-I0");
                System.out.println("1-I1");
                System.out.print("Choose id: ");
                id = sc.next();
                id = "I"+id;
                while(true){
                    System.out.println("Welcome " + id);
                    System.out.println("INSTRUCTOR MENU");
                    System.out.println("1. Add Class Material");
                    System.out.println("2. Add Assessments");
                    System.out.println("3. View Lecture Materials");
                    System.out.println("4. View Assessments");
                    System.out.println("5. Grade Assessments");
                    System.out.println("6. Close Assessments");
                    System.out.println("7. View Comments");
                    System.out.println("8. Add Comments");
                    System.out.println("9. Logout");
                    int n2 = 0;
                    n2 = sc.nextInt();
                    if (n2==9)
                        break;
                    else if(n2==1){
                        System.out.println("1. Add Lecture Slide");
                        System.out.println("2. Add Lecture Video");
                        s1.class_material(id);
                        naam.add(id);
                    }
                    else if (n2==2){
                        System.out.println("1. Add Assignment");
                        System.out.println("2. Add Quiz");
                        s1.asessments();
                    }
                    else if (n2==3)
                        s1.lecture_material();
                    else if (n2==4)
                        s1.view();
                    else if (n2==5)
                        s1.grade(id);
                    else if (n2==6)
                        s1.close();
                    else if (n2==7)
                        s2.view_comments();
                    else if (n2==8)
                        s2.add_comments(id);
                }
            }
            else{
                System.out.println("Students:");
                System.out.println("0-S0");
                System.out.println("1-S1");
                System.out.println("2-S2");
                System.out.print("Choose id: ");
                id = sc.next();
                id = "S"+id;
                while (true){
                    System.out.println("Welcome "+id);
                    System.out.println("STUDENT MENU");
                    System.out.println("1. View Lecture Materials");
                    System.out.println("2. View Assessments");
                    System.out.println("3. Submit Assessments");
                    System.out.println("4. View Grades");
                    System.out.println("5. View Comments");
                    System.out.println("6. Add Comments");
                    System.out.println("7. Logout");
                    int n2 = 0;
                    n2 = sc.nextInt();
                    if (n2==7)
                        break;
                    else if (n2==1)
                        s1.lecture_material();
                    else if(n2==2)
                        s1.view();
                    else if(n2==3)
                        s1.submit(id);
                    else if(n2==4)
                        s1.view_grades(id);
                    else if(n2==5)
                        s2.view_comments();
                    else if(n2==6)
                        s2.add_comments(id);
                }
            }
        }
    }
}
class instructor implements material,assessments{
    Scanner sc = new Scanner(System.in);
    int slides = 0;
    int videos = 0;
    int assignments = 0;
    int quizzes = 0;
    ArrayList <ArrayList<Object>> slide = new ArrayList<ArrayList<Object>>();
    ArrayList <ArrayList<Object>> video = new ArrayList<ArrayList<Object>>();
    ArrayList <ArrayList<Object>> assignment = new ArrayList<ArrayList<Object>>();
    ArrayList <ArrayList<Object>> quiz = new ArrayList<ArrayList<Object>>();
    @Override
    public void class_material(String id) {
        int num = sc.nextInt();
        if (num==1){
            slide.add(new ArrayList<>());
            System.out.print("Enter topic of slides: ");
            String topic1 = sc.nextLine();
            topic1+=sc.nextLine();
            slide.get(slides).add(topic1);
            System.out.print("Enter number of slides: ");
            int number = sc.nextInt();
            slide.get(slides).add(number);
            System.out.println("Enter content of slides");
            for (int i=1; i<=number; i++){
                System.out.print("Content of slide "+i+": ");
                String content = sc.nextLine();
                content+=sc.nextLine();
                slide.get(slides).add(content);
            }
            slide.get(slides).add("I"+id);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            slide.get(slides).add(dtf.format(now));
            slides++;
        }
        else{
            System.out.print("Enter topic of video: ");
            String topic2 = sc.nextLine();
            topic2+=sc.nextLine();
            System.out.print("Enter filename of video: ");
            String name = sc.nextLine();
            name+= sc.nextLine();
            video.add(new ArrayList<>());
            video.get(videos).add(topic2);
            video.get(videos).add(name);
            video.get(videos).add("I"+id);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            video.get(videos).add(dtf.format(now));
            videos++;
        }
    }

    @Override
    public void lecture_material() {
        for (int i=0; i<slide.size(); i++){
            System.out.println("Title: "+slide.get(i).get(0));
            for (int k=1; k<=((int)(slide.get(i).get(1))); k++){
                System.out.println("Slide "+k+": "+slide.get(i).get(k+1));
            }
            System.out.println("Number of slides: "+slide.get(i).get(1));
            int s = slide.get(i).size()-1;
            System.out.println("Date of upload: "+slide.get(i).get(s));
            System.out.println("Uploaded by: "+slide.get(i).get(s-1));
            System.out.println();
        }
        for (int i=0; i<video.size(); i++){
            System.out.println("Title of video: "+video.get(i).get(0));
            System.out.println("Video file: "+video.get(i).get(1));
            System.out.println("Date of upload: "+video.get(i).get(3));
            System.out.println("Uploaded by: "+video.get(i).get(2));
            System.out.println();
        }
    }

    @Override
    public void asessments(){
        int num = sc.nextInt();
        if (num==1){
            assignment.add(new ArrayList<>());
            System.out.print("Enter problem statement: ");
            String prob = sc.nextLine();
            prob+= sc.nextLine();
            assignment.get(assignments).add(prob);
            System.out.print("Enter max marks: ");
            int marks = sc.nextInt();
            assignment.get(assignments).add(marks);
            assignment.get(assignments).add(0);
            assignment.get(assignments).add(0);
            assignment.get(assignments).add(0);
            assignment.get(assignments).add("name");
            assignment.get(assignments).add("name");
            assignment.get(assignments).add("name");
            assignment.get(assignments).add(0);
            assignment.get(assignments).add(0);
            assignment.get(assignments).add(0);
            assignment.get(assignments).add(0);
            assignment.get(assignments).add(0);
            assignment.get(assignments).add(0);
            assignments++;
        }
        else {
            quiz.add(new ArrayList<>());
            System.out.print("Enter quiz question: ");
            String ques = sc.nextLine();
            ques+= sc.nextLine();
            quiz.get(quizzes).add(ques);
            quiz.get(quizzes).add(0);
            quiz.get(quizzes).add(0);
            quiz.get(quizzes).add(0);
            quiz.get(quizzes).add("name");
            quiz.get(quizzes).add("name");
            quiz.get(quizzes).add("name");
            quiz.get(quizzes).add(0);
            quiz.get(quizzes).add(0);
            quiz.get(quizzes).add(0);
            quiz.get(quizzes).add(0);
            quiz.get(quizzes).add(0);
            quiz.get(quizzes).add(0);
            quizzes++;
        }
    }

    @Override
    public void view() {
        for(int i=0; i<assignment.size(); i++){
            System.out.println("ID: "+i+" Assignment: "+assignment.get(i).get(0)+" Max marks: "+assignment.get(i).get(1));
            System.out.println("-----------------");
        }
        for (int i=0; i<quiz.size(); i++){
            System.out.println("ID: "+(i+assignment.size())+" Question: "+quiz.get(i).get(0));
            System.out.println("-----------------");
        }
    }

    @Override
    public void submit(String id) {
        System.out.println("Pending assessments");
        int index = 0;
        int stu = -1;
        if (id.contains("0")) {
            for (int i = 0; i < assignment.size(); i++) {
                if (((int)assignment.get(i).get(2)) == 0) {
                    System.out.println("ID: " + index + " Assignment: " + assignment.get(i).get(0) + " Max marks: " + assignment.get(i).get(1));
                    index++;
                    stu = 0;
                }
            }
            for (int i = 0; i < quiz.size(); i++) {
                if (((int) quiz.get(i).get(2)) == 0) {
                    System.out.println("ID: " + (i + assignment.size()) + " Question: " + quiz.get(i).get(0));
                    index++;
                    stu = 0;
                }
            }
        }
        else if (id.contains("1")) {
            for (int i = 0; i < assignment.size(); i++) {
                if (((int) assignment.get(i).get(3)) == 0) {
                    System.out.println("ID: " + index + " Assignment: " + assignment.get(i).get(0) + " Max marks: " + assignment.get(i).get(1));
                    index++;
                    stu = 1;
                }
            }
            for (int i = 0; i < quiz.size(); i++) {
                if (((int) quiz.get(i).get(3)) == 0) {
                    System.out.println("ID: " + (i + assignment.size()) + " Question: " + quiz.get(i).get(0));
                    index++;
                    stu = 1;
                }
            }
        }
        else if (id.contains("2")) {
            for (int i = 0; i < assignment.size(); i++) {
                if (((int) assignment.get(i).get(4)) == 0) {
                    System.out.println("ID: " + index + " Assignment: " + assignment.get(i).get(0) + " Max marks: " + assignment.get(i).get(1));
                    index++;
                    stu = 2;
                }
            }
            for (int i = 0; i < quiz.size(); i++) {
                if (((int) quiz.get(i).get(4)) == 0) {
                    System.out.println("ID: " + (i + assignment.size()) + " Question: " + quiz.get(i).get(0));
                    index++;
                    stu = 2;
                }
            }
        }
        System.out.print("Enter ID of assessment: ");
        int ide = sc.nextInt();
        int save = -1;
        for (int i=0; i<assignment.size(); i++){
            if (i==ide){
                save = i;
                break;
            }
        }
        if (save==-1){
            for (int i=0; i<quiz.size(); i++){
                if ((i+assignment.size())==ide){
                    save = ide;
                    break;
                }
            }
        }
        String name = "";
        if (save<assignment.size()){
            while (true) {
                System.out.print("Enter filename of assignment: ");
                name = sc.nextLine();
                name += sc.nextLine();
                if(name.contains("zip")||name.contains("mp4"))
                    break;
                else
                    System.out.println("Not a valid submission.");
            }
            for (int i=0; i<assignment.size(); i++){
                if(i==save){
                    assignment.get(i).set(stu+2, name);
                    assignment.get(i).set(assignment.get(i).size()-2-(3-stu), 1);
                    break;
                }
            }
        }
        else {
            System.out.print(quiz.get(ide-assignment.size()).get(0));
            name = sc.nextLine();
            name += sc.nextLine();
            for (int i=0; i<quiz.size(); i++){
                if ((i+assignment.size())==save){
                    quiz.get(i).set(stu+2, name);
                    quiz.get(i).set(quiz.get(i).size()-2-(3-stu), 1);
                    break;
                }
            }
        }
    }

    @Override
    public void grade(String id) {
        System.out.println("List of assessments");
        view();
        int index = 0;
        System.out.print("Enter ID of assessment to view submissions: ");
        int assess = sc.nextInt();
        int[] arr1 = new int[3];
        System.out.println("Choose ID from these ungraded submissions");
        if (assess<assignment.size()){
            for (int i=2; i<assignment.get(assess).size(); i++) {
                int check = (int)assignment.get(assess).get(i);
                if(check==1) {
                    if(i==2) {
                        System.out.println(index + ". S0");
                        arr1[0] = 8;
                    }
                    else if (i==3) {
                        System.out.println(index + ". S1");
                        arr1[1] = 8;
                    }
                    else if (i==4) {
                        System.out.println(index + ". S2");
                        arr1[2] = 8;
                    }
                }
            }
        }
        else{
            for (int i=2; i<(assess-assignment.size()); i++){
                int check = (int)quiz.get(assess).get(i);
                if (check==1){
                    if(i==2) {
                        System.out.println(index + ". S0");
                        arr1[0] = 8;
                    }
                    else if (i==3) {
                        System.out.println(index + ". S1");
                        arr1[1] = 8;
                    }
                    else if (i==4) {
                        System.out.println(index + ". S2");
                        arr1[2] = 8;
                    }
                }
            }
        }
        int k=0;
        int i = sc.nextInt();
        int safe = 0;
        for (int j=0; j<=i; j++){
            if (assess<assignment.size()){
                for (k=0; k<=i; ){
                    if (arr1[k]==8) {
                        safe = k;
                        k++;
                    }
                }
            }
            else {
                for (k=0; k<=i; ){
                    if (arr1[k]==8) {
                        safe = k;
                        k++;
                    }
                }
            }
        }
        System.out.println("Submission:");
        System.out.println("Submission: "+assignment.get(assess).get(k+2));
        System.out.println("---------------------");
        System.out.println("Max marks: "+assignment.get(assess).get(1));
        System.out.print("Marks scored: ");
        int marks = sc.nextInt();
        if(assess<assignment.size()) {
            assignment.get(assess).set(k + 8, marks);
            assignment.get(assess).set(k + 11, id);
        }
        else{
            quiz.get(assess).set(k+7, marks);
            quiz.get(assess).set(k+10, id);
        }
    }

    @Override
    public void close() {
        int index = 0;
        ArrayList <ArrayList<Object>> close1 = new ArrayList<>();
        ArrayList <ArrayList<Object>> close2 = new ArrayList<>();
        System.out.println("List of open assignments:");
        view();
        System.out.println("Enter ID of assignment to close: ");
        int asignment = sc.nextInt();
        if(asignment<assignment.size()) {
            close1.add(new ArrayList<>());
            for (int i = 0; i < assignment.get(asignment).size(); i++) {
                close1.get(index).add(assignment.get(asignment).get(i));
            }
            assignment.remove(asignment);
        }
        else {
            close2.add(new ArrayList<>());
            for (int i=0; i<quiz.get(asignment-assignment.size()).size(); i++){
                close2.get(index).add(quiz.get(asignment-assignment.size()).get(i));
            }
            assignment.remove(asignment - assignment.size());
        }
    }

    @Override
    public void view_grades(String id) {
        int k = -1;
        if (id.contains("0"))
            k = 0;
        else if(id.contains("1"))
            k = 1;
        else if (id.contains("2"))
            k = 2;
        System.out.println("Graded submissions");
        for (int i=0; i<assignment.size(); i++){
            for (int j=8; j<11; j++){
                if ((int)assignment.get(i).get(j)>0){
                    System.out.println("Submission: "+assignment.get(i).get(5+k));
                    System.out.println("Marks scored: "+assignment.get(i).get(8+k));
                    System.out.println("Graded by: "+assignment.get(i).get(11+k));
                }
            }
        }
        for (int i=0; i<quiz.size(); i++){
            for (int j=8; j<11; j++){
                if ((int)quiz.get(i).get(j)>0){
                    System.out.println("Submission: "+assignment.get(i).get(4+k));
                    System.out.println("Marks scored: "+assignment.get(i).get(7+k));
                    System.out.println("Graded by: "+assignment.get(i).get(10+k));
                }
            }
        }
        System.out.println("---------------------");
        System.out.println("Ungraded submission:");
        for (int i=0; i<assignment.size(); i++){
            for (int j=2; j<5; j++){
                if((int)assignment.get(i).get(j)==0){
                    System.out.println("Submission: "+assignment.get(i).get(5+k));
                }
            }
        }
        for (int i=0; i<quiz.size(); i++){
            for (int j=2; j<5; j++){
                if((int)assignment.get(i).get(j)==0){
                    System.out.println("Submission: "+quiz.get(i).get(5+k));
                }
            }
        }
    }
}
class common implements comments{
    Scanner sc = new Scanner(System.in);
    int index = 0;
    ArrayList <ArrayList<Object>> comment = new ArrayList<ArrayList<Object>>();

    @Override
    public void add_comments(String id) {
        comment.add(new ArrayList<>());
        System.out.print("Enter comment: ");
        String comments = sc.nextLine();
        comments += sc.nextLine();
        comment.get(index).add(comments);
        comment.get(index).add(id);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        comment.get(index).add(dtf.format(now));
        index++;
    }

    @Override
    public void view_comments() {
        for (int i=0; i<comment.size(); i++){
            System.out.println(comment.get(i).get(0)+" - "+comment.get(i).get(1));
            System.out.println(comment.get(i).get(2));
            System.out.println();
        }
    }
}