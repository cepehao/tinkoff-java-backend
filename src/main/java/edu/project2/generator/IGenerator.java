package edu.project2.generator;

import edu.project2.model.CMaze;

public interface IGenerator {
    CMaze generate(int height, int width);
}
