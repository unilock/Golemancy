package net.emirikol.golemancy.screen;

import net.emirikol.golemancy.genetics.SerializedGenome;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class SoulMirrorScreen extends HandledScreen<ScreenHandler> {
    public static final int TITLE_Y = 10;
    public static final int COLUMN_HEADER_Y = 35;
    public static final int COLUMN_DOM_X = 70;
    public static final int COLUMN_REC_X = 120;
    public static final int ROW_START_X = 15;
    public static final int TYPE_ROW_Y = 50;
    public static final int POTENCY_ROW_Y = 60;
    public static final int STRENGTH_ROW_Y = 70;
    public static final int AGILITY_ROW_Y = 80;
    public static final int VIGOR_ROW_Y = 90;
    public static final int SMARTS_ROW_Y = 100;
    //A path to the GUI texture to use.
    private static final Identifier TEXTURE = new Identifier("golemancy", "textures/gui/container/soul_mirror.png");

    public SoulMirrorScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(GuiGraphics graphics, float delta, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        graphics.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    @Override
    protected void drawForeground(GuiGraphics graphics, int mouseX, int mouseY) {
        //Get genome data from server and deserialize.
        String soulData = ((SoulMirrorScreenHandler) this.handler).getSoulData();
        SerializedGenome serializedGenome = new SerializedGenome(soulData);
        //Draw title.
        Text titleText = Text.translatable("item.golemancy.soul_mirror");
        int x = (backgroundWidth - textRenderer.getWidth(titleText)) / 2;
        graphics.drawShadowedText(this.textRenderer, titleText, x, TITLE_Y, 4210752);
        //Draw column headers.
        Text activeText = Text.translatable("text.golemancy.active_column");
        Text dormantText = Text.translatable("text.golemancy.dormant_column");
        graphics.drawShadowedText(this.textRenderer, activeText, COLUMN_DOM_X, COLUMN_HEADER_Y, 0xff0000);
        graphics.drawShadowedText(this.textRenderer, dormantText, COLUMN_REC_X, COLUMN_HEADER_Y, 0x00acff);
        //Draw type row.
        Text typeText = Text.translatable("text.golemancy.type");
        graphics.drawShadowedText(this.textRenderer, typeText, ROW_START_X, TYPE_ROW_Y, 4210752);
        graphics.drawShadowedText(this.textRenderer, typeToText(serializedGenome.activeAlleles.get("type")), COLUMN_DOM_X, TYPE_ROW_Y, 4210752);
        graphics.drawShadowedText(this.textRenderer, typeToText(serializedGenome.dormantAlleles.get("type")), COLUMN_REC_X, TYPE_ROW_Y, 4210752);
        //Draw potency row.
        Text potencyText = Text.translatable("text.golemancy.potency");
        graphics.drawShadowedText(this.textRenderer, potencyText, ROW_START_X, POTENCY_ROW_Y, 4210752);
        graphics.drawShadowedText(this.textRenderer, potencyToText(serializedGenome.activeAlleles.get("potency")), COLUMN_DOM_X, POTENCY_ROW_Y, 4210752);
        graphics.drawShadowedText(this.textRenderer, potencyToText(serializedGenome.dormantAlleles.get("potency")), COLUMN_REC_X, POTENCY_ROW_Y, 4210752);
        //Draw strength row.
        Text strengthText = Text.translatable("text.golemancy.strength");
        graphics.drawShadowedText(this.textRenderer, strengthText, ROW_START_X, STRENGTH_ROW_Y, 4210752);
        graphics.drawShadowedText(this.textRenderer, geneToText(serializedGenome.activeAlleles.get("strength")), COLUMN_DOM_X, STRENGTH_ROW_Y, 4210752);
        graphics.drawShadowedText(this.textRenderer, geneToText(serializedGenome.dormantAlleles.get("strength")), COLUMN_REC_X, STRENGTH_ROW_Y, 4210752);
        //Draw agility row.
        Text agilityText = Text.translatable("text.golemancy.agility");
        graphics.drawShadowedText(this.textRenderer, agilityText, ROW_START_X, AGILITY_ROW_Y, 4210752);
        graphics.drawShadowedText(this.textRenderer, geneToText(serializedGenome.activeAlleles.get("agility")), COLUMN_DOM_X, AGILITY_ROW_Y, 4210752);
        graphics.drawShadowedText(this.textRenderer, geneToText(serializedGenome.dormantAlleles.get("agility")), COLUMN_REC_X, AGILITY_ROW_Y, 4210752);
        //Draw vigor row.
        Text vigorText = Text.translatable("text.golemancy.vigor");
        graphics.drawShadowedText(this.textRenderer, vigorText, ROW_START_X, VIGOR_ROW_Y, 4210752);
        graphics.drawShadowedText(this.textRenderer, geneToText(serializedGenome.activeAlleles.get("vigor")), COLUMN_DOM_X, VIGOR_ROW_Y, 4210752);
        graphics.drawShadowedText(this.textRenderer, geneToText(serializedGenome.dormantAlleles.get("vigor")), COLUMN_REC_X, VIGOR_ROW_Y, 4210752);
        //Draw smarts row.
        Text smartsText = Text.translatable("text.golemancy.smarts");
        graphics.drawShadowedText(this.textRenderer, smartsText, ROW_START_X, SMARTS_ROW_Y, 4210752);
        graphics.drawShadowedText(this.textRenderer, geneToText(serializedGenome.activeAlleles.get("smarts")), COLUMN_DOM_X, SMARTS_ROW_Y, 4210752);
        graphics.drawShadowedText(this.textRenderer, geneToText(serializedGenome.dormantAlleles.get("smarts")), COLUMN_REC_X, SMARTS_ROW_Y, 4210752);
    }

    @Override
    public void render(GuiGraphics matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
    }

    public MutableText typeToText(String type) {
        return Text.translatable(type);
    }

    public Text geneToText(String geneValue) {
        switch (geneValue) {
            case "0":
                return Text.translatable("text.golemancy.gene_low");
            case "1":
                return Text.translatable("text.golemancy.gene_average");
            case "2":
                return Text.translatable("text.golemancy.gene_high");
            case "3":
                return Text.translatable("text.golemancy.gene_perfect");
            default:
                return Text.literal("???");
        }
    }

    public Text potencyToText(String geneValue) {
        switch (geneValue) {
            case "1":
                return Text.translatable("text.golemancy.gene_feeble");
            case "2":
                return Text.translatable("text.golemancy.gene_low");
            case "3":
                return Text.translatable("text.golemancy.gene_average");
            case "4":
                return Text.translatable("text.golemancy.gene_high");
            case "5":
                return Text.translatable("text.golemancy.gene_perfect");
            default:
                return Text.literal("???");
        }
    }
}