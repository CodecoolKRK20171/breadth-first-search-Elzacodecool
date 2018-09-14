package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserDAO;
import com.codecool.bfsexample.model.UserNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BreadthFirstSearchTest {
    private UserDAO userDAO = new UserDAO();

    @Test
    void testDistance() {
        UserNode firstUser = userDAO.getById(1);
        UserNode secondUser = userDAO.getById(2);
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
        int result = 1;

        assertEquals(result, breadthFirstSearch.distance(firstUser, secondUser));
    }

    @Test
    void testDistance_4() {
        UserNode firstUser = userDAO.getById(1);
        UserNode secondUser = userDAO.getById(112);
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
        int distance = breadthFirstSearch.distance(firstUser, secondUser);
        int result = 4;

        assertEquals(result, distance);
    }

    @Test
    void testGetListFriendsByDistance() {
        UserNode user = userDAO.getById(1);
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
        int distance = 1;
        Set<UserNode> friends = breadthFirstSearch.getListFriendsByDistance(user, distance);
        String friendsId = getFriendsId(friends);
        String result = " 2 18 56 61";

        assertEquals(result, friendsId);
    }

    private String getFriendsId(Set<UserNode> friends) {
        List<UserNode> sortedList = friends.stream()
                .sorted(Comparator.comparing(UserNode::getId))
                .collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();

        for (UserNode userNode : sortedList) {
            stringBuilder.append(" ").append(userNode.getId());
        }
        return stringBuilder.toString();
    }


}