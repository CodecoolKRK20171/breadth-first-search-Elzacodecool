package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import java.util.*;

public class BreadthFirstSearch {
    public int distance(UserNode firstUser, UserNode secondUser) {
        Map<UserNode, Integer> mapUsersDistance = new HashMap<>();
        Queue<UserNode> userNodeQueue = new LinkedList<>();
        userNodeQueue.add(firstUser);
        mapUsersDistance.put(firstUser, 0);
        UserNode user;
        int distance;

        while (userNodeQueue.peek() != null) {
            user = userNodeQueue.poll();
            distance = mapUsersDistance.get(user);

            if (user.getId() == secondUser.getId()) {
                return mapUsersDistance.get(user);
            }

            for (UserNode userFriend : user.getFriends()) {
                if (!mapUsersDistance.containsKey(userFriend)) {
                    userNodeQueue.add(userFriend);
                    mapUsersDistance.put(userFriend, distance + 1);
                }
            }
        }
        return 0;
    }

    public Set<UserNode> getListFriendsByDistance(UserNode user, int distance) {
        Map<UserNode, Integer> mapUsersDistance = new HashMap<>();
        Queue<UserNode> userNodeQueue = new LinkedList<>();
        userNodeQueue.add(user);
        mapUsersDistance.put(user, 0);
        UserNode userNode;
        int userNodeDistance;

        while (userNodeQueue.peek() != null) {
            userNode = userNodeQueue.poll();
            userNodeDistance =  mapUsersDistance.get(userNode);

            if (userNodeDistance == distance) {
                break;
            }

            for (UserNode userNodeFriend : userNode.getFriends()) {
                if (!mapUsersDistance.containsKey(userNodeFriend)) {
                    userNodeQueue.add(userNodeFriend);
                    mapUsersDistance.put(userNodeFriend, userNodeDistance + 1);
                }
            }
        }
        mapUsersDistance.remove(user);
        return mapUsersDistance.keySet();
    }
}
