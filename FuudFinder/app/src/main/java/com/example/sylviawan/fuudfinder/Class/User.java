package com.example.sylviawan.fuudfinder.Class;

public class User {
        public String name;
        public String email;
        public String uid;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }


    public User(String uid, String name, String email) {
            this.uid = uid;
            this.name = name;
            this.email = email;
        }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUid() {
        return uid;
    }
}
