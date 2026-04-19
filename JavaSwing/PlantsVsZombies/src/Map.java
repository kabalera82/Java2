package src;

import src.gameConstants.*;
import src.plants.Plant;
import src.zombies.Zombie;

import java.util.ArrayList;
import java.util.List;

public class Map {

    //
    private List <Plant> plants = new ArrayList<>();
    private List<Zombie> zombies = new ArrayList<>();
    private List <Projectile> projectiles = new ArrayList<>();
    private List<Sun> sunshine = new ArrayList <>();

    // Sol at player disponse
    private int sunPoints = 50;

    // Verifi if Tile is occupied
    public boolean isCellOccupied(int row, int col) {
        for (Plant p : plants) {
            if(p.getRow() == row && p.getCol() == col) return true;
        }
        return false;
    }


}
