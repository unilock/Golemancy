package net.emirikol.golemancy.genetics;

import net.emirikol.golemancy.entity.AbstractGolemEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.text.Text;

/**
 * @param typeString the translation key associated with the soul, i.e. "text.golemancy.type.covetous"
 * @param entityType the golem type spawned by the soul
 */
public record SoulType(String typeString, EntityType<? extends AbstractGolemEntity> entityType) {
    public Text getTypeText() {
        return Text.translatable(this.typeString);
    }

    @Override
    public String toString() {
        return typeString();
    }
}
