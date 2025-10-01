package gameplay;

import params.EnemySpawnParam;

import java.util.List;

public record Wave(List<EnemySpawnParam> spawns) {
}
