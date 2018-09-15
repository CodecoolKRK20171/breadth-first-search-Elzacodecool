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

    public List<List<UserNode>> shortestPaths(UserNode firstUser, UserNode secondUser) {
        List<List<UserNode>> paths = new ArrayList<>();

        Map<UserNode, List<List<UserNode>>> mapUsersAndPaths = initMapUsersAndPaths(firstUser);
        Queue<UserNode> userNodeQueue = initQueue(firstUser);

        UserNode user = userNodeQueue.poll();
        int distance = distance(firstUser, secondUser);

        while (isSmallDistance(mapUsersAndPaths.get(user), distance)) {
            for (UserNode userNodeFriend : user.getFriends()) {
                if (!mapUsersAndPaths.containsKey(userNodeFriend)) {
                    mapUsersAndPaths.put(userNodeFriend, new ArrayList<>());
                }
                for (List<UserNode> path : mapUsersAndPaths.get(user)) {
                    if (!path.contains(userNodeFriend)) {
                        List<List<UserNode>> listPaths = mapUsersAndPaths.get(userNodeFriend);
                        List<UserNode> newPath = new LinkedList<>(path);
                        newPath.add(userNodeFriend);
                        listPaths.add(newPath);
                    }

                }
                if (!userNodeQueue.contains(userNodeFriend)) {
                    userNodeQueue.add(userNodeFriend);
                }
            }

            if (user.getId() == secondUser.getId()) {
                paths.addAll(mapUsersAndPaths.get(user));
            }
            user = userNodeQueue.poll();
        }

        return paths;
    }

    private boolean isSmallDistance(List<List<UserNode>> paths, int distance) {
        return paths.size() == 0 || paths.get(0).size() <= distance + 1;
    }

    private Map<UserNode, List<List<UserNode>>> initMapUsersAndPaths(UserNode firstUser) {
        Map<UserNode, List<List<UserNode>>> mapUsersAndPaths = new HashMap<>();
        List<List<UserNode>> firstUserPaths = new ArrayList<>();
        List<UserNode> firstUserPath = new LinkedList<>();
        firstUserPath.add(firstUser);
        firstUserPaths.add(firstUserPath);
        mapUsersAndPaths.put(firstUser, firstUserPaths);

        return mapUsersAndPaths;
    }

    private Queue<UserNode> initQueue(UserNode firstUser) {
        Queue<UserNode> userNodeQueue = new LinkedList<>();
        userNodeQueue.add(firstUser);
        return userNodeQueue;
    }


}
