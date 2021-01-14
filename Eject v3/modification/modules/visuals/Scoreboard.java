package modification.modules.visuals;

import modification.enummerates.Category;
import modification.extenders.Module;
import modification.extenders.Value;
import modification.interfaces.Event;

public final class Scoreboard
        extends Module {
    public final Value<Float> offset = new Value("Offset", Float.valueOf(0.0F), 0.0F, 150.0F, 0, this, new String[0]);

    public Scoreboard(String paramString, Category paramCategory) {
        super(paramString, paramCategory);
    }

    protected void onActivated() {
    }

    public void onEvent(Event paramEvent) {
    }

    protected void onDeactivated() {
    }
}




