package com.artlite.ckconcept.constants;

import android.support.annotation.NonNull;

import com.artlite.ckconcept.models.widget.KitWidgetModel;

/**
 * Class which provide the priority for the {@link KitWidgetModel}
 */
public enum KitWidgetPriority {
    MINIMUM, LOW, MIDDLE, HIGH, MAXIMUM;

    /**
     * Method which provide the checking if current priority is more then compared
     *
     * @param priority instance of the {@link KitWidgetPriority}
     * @return comparing result
     */
    public boolean moreThan(@NonNull KitWidgetPriority priority) {
        return this.ordinal() > priority.ordinal();
    }

    /**
     * Method which provide the checking if current priority is less then compared
     *
     * @param priority instance of the {@link KitWidgetPriority}
     * @return comparing result
     */
    public boolean lessThan(@NonNull KitWidgetPriority priority) {
        return this.ordinal() < priority.ordinal();
    }

    /**
     * Method which provide the checking if current priority is equal to compared
     *
     * @param priority instance of the {@link KitWidgetPriority}
     * @return comparing result
     */
    public boolean isEqual(@NonNull KitWidgetPriority priority) {
        return this.ordinal() == priority.ordinal();
    }
}
