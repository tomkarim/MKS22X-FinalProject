
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public Dimension size;
    public Point2D scale;

    public List<Actor> actors = new ArrayList<Actor>();

    public void initialize() {
    }

    public void update() {
        for (Actor actor : actors) {
            actor.update();
        }
    }

    public void draw(Graphics2D graph) {
        for (Actor actor : actors) {
            actor.draw(graph);
        }
    }

    public <Typ> Typ collisionChecker(Actor a1, Class<Typ> type) {
        a1.updateCollider();
        for(Actor a2 : actors) {
            a2.updateCollider();
            if (a1 != a2
                    && type.isInstance(a2)
                    && a1.collider != null && a2.collider != null
                    && a1.visible && a2.visible
                    && a2.collider.intersects(a1.collider)) {
                return type.cast(a2);
            }
        }
        return null;
    }
    public void broadcastMessage(String message) {
        for (Actor obj : actors) {
            try {
                Method method = obj.getClass().getMethod(message);
                if (method != null) {
                    method.invoke(obj);
                }
            } catch (Exception ex) {
            }
        }
    }
}
