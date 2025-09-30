package gameplay;

import params.EnemySpawnParam;

import java.util.List;

public class Wave {
    private final List<EnemySpawnParam> spawns;

    public Wave(List<EnemySpawnParam> spawns) {
        this.spawns = spawns;
    }

    public List<EnemySpawnParam> getSpawns() {
        return spawns;
    }
}
