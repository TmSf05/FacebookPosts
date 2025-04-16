/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.facebookapp;

/**
 *
 * @author User
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FacebookApp {
    private static final String FILE_NAME = "FacebookPosts.txt";
    private static final ArrayList<String> posts = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("Welcome to Facebook!");
       
        while (running) {
            try {
                showMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        createPost();
                        break;
                    case 2:
                        viewPosts();
                        break;
                    case 3:
                        deletePost();
                        break;
                    case 4:
                        searchPosts();
                        break;
                    case 5:
                        savePostsToFile();
                        System.out.println("Exiting... Posts saved.");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!");
                scanner.nextLine(); // clear invalid input
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n===============================");
        System.out.println("Choose an option:");
        System.out.println("1. Create Post");
        System.out.println("2. View All Posts");
        System.out.println("3. Delete a Post");
        System.out.println("4. Search Posts");
        System.out.println("5. Exit");
        System.out.println("===============================");
        System.out.print("\nEnter choice: ");
        
    }

    private static void createPost() {
        System.out.print("Enter your post: ");
        String post = scanner.nextLine();
        if (post.trim().isEmpty()) {
            System.out.println("Post cannot be empty.");
        } else {
            posts.add(post);
            System.out.println("Post added!");
        }
    }

    private static void viewPosts() {
        if (posts.isEmpty()) {
            System.out.println("No posts yet.");
        } else {
            System.out.println("\n=========[ All Posts ]=========");
            for (int i = 0; i < posts.size(); i++) {
                System.out.println((i + 1) + ". " + posts.get(i));
            }
        }
    }

    private static void deletePost() {
        viewPosts();
        if (posts.isEmpty()) return;

        try {
             System.out.println("===============================\n");
            System.out.print("Enter the post number to delete: ");
            int postIndex = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (postIndex < 1 || postIndex > posts.size()) {
                System.out.println("Invalid post number.");
            } else {
                String removed = posts.remove(postIndex - 1);
                System.out.println("Deleted post: \"" + removed + "\"");
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number.");
            scanner.nextLine(); // clear invalid input
        }
    }

    private static void searchPosts() {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).toLowerCase().contains(keyword)) {
                if (!found) {
                    System.out.println("\n======[ Matching Posts ]======");
                    found = true;
                }
                System.out.println((i + 1) + ". " + posts.get(i));
            }
        }

        if (!found) {
            System.out.println("No posts found with the keyword \"" + keyword + "\".");
        }
    }

    private static void savePostsToFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (String post : posts) {
                writer.write(post + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving posts to file: " + e.getMessage());
        }
    }
}


