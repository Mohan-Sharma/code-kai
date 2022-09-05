package com.code.kai.leetcode.dojo.medium.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Mohan Sharma
 */
public class Twitter {

    Map<Integer, User> storage = new HashMap<>();

    public Twitter() {

    }

    public void postTweet(int userId, int tweetId) {
        User user = storage.getOrDefault(userId, new User());
        Tweet tweet = new Tweet(tweetId);
        user.pq.add(tweet);
        if (user.pq.size() > 10)
            user.pq.poll();
        storage.put(userId, user);
    }

    public List<Integer> getNewsFeed(int userId) {
        User user = storage.getOrDefault(userId, new User());
        PriorityQueue<Tweet> pq = new PriorityQueue<>(10, (t1, t2) -> t1.millis > t2.millis ? -1 : 1);
        while (!user.pq.isEmpty()) {
            pq.add(user.pq.poll());
        }

        for (User  follower : user.followers) {
            pq.addAll(follower.pq);
        }
        int size = 10;
        List<Integer> result = new ArrayList<>(size);
        while (!pq.isEmpty() && size-- > 0) {
            result.add(pq.poll().tweetId);
        }
        return result;
    }

    public void follow(int followerId, int followeeId) {
        User follower = storage.getOrDefault(followerId, new User());
        User followee = storage.getOrDefault(followeeId, new User());
        follower.followers.add(followee);
        storage.put(followerId, follower);
        storage.put(followeeId, followee);
    }

    public void unfollow(int followerId, int followeeId) {
        User follower = storage.getOrDefault(followerId, new User());
        User followee = storage.getOrDefault(followeeId, new User());
        follower.followers.remove(followee);
    }

    class User {
        PriorityQueue<Tweet> pq = new PriorityQueue<>(10, Comparator.comparingLong(t -> t.millis));
        List<User> followers = new ArrayList<>();
    }

    class Tweet {
        int tweetId;
        long millis;

        public Tweet(int tweetId) {
            this.tweetId = tweetId;
            this.millis = System.currentTimeMillis();
        }

        public long getMillis() {
            return millis;
        }
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5);
        twitter.postTweet(1, 3);
        System.out.println(twitter.getNewsFeed(1));
    }
}
