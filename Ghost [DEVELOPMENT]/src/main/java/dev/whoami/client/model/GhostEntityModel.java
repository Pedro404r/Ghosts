package dev.whoami.client.model;

import dev.whoami.entity.GhostEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;

public class GhostEntityModel extends SinglePartEntityModel<GhostEntity>{
    // declarar todas as partes do modelo
    private final ModelPart root;
    private final ModelPart Asas;
    private final ModelPart Capa;
    private final ModelPart Torso;
    private final ModelPart Cabeca;
    private final ModelPart OssoExposto;
    private final ModelPart OssosPerna;
    private final ModelPart Pe;

    public GhostEntityModel(ModelPart root) {
        this.root = root;
        this.Asas = root.getChild("Asas");
        this.Capa = root.getChild("Capa");
        this.Torso = root.getChild("Torso");
        this.Cabeca = root.getChild("Cabeca");
        this.OssoExposto = root.getChild("OssoExposto");
        this.OssosPerna = root.getChild("OssosPerna");
        this.Pe = root.getChild("Pe");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        // ASAS
        ModelPartData Asas = modelPartData.addChild("Asas",
                ModelPartBuilder.create().uv(16, 15).cuboid(-7.0F, -3.8F, -1.0F, 5.0F, 2.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(3.0F, 14.6F, 0.0F));

        ModelPartData cube_r1 = Asas.addChild("cube_r1",
                ModelPartBuilder.create().uv(0, 21).cuboid(-3.6728F, 0.5403F, -1.0F, 5.0F, 2.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(3.9F, -1.2F, -1.1F, 3.1052F, -0.0377F, -2.373F));

        ModelPartData cube_r2 = Asas.addChild("cube_r2",
                ModelPartBuilder.create().uv(16, 9).cuboid(-4.7F, -2.6F, -1.0F, 6.0F, 2.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(3.9F, -1.2F, -1.1F, -3.1416F, -0.0524F, 3.1416F));

        ModelPartData cube_r3 = Asas.addChild("cube_r3",
                ModelPartBuilder.create().uv(16, 12).cuboid(-4.1828F, -1.1099F, -1.0F, 6.0F, 2.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(3.9F, -1.2F, -1.1F, 3.1211F, -0.0482F, -2.7397F));

        ModelPartData cube_r4 = Asas.addChild("cube_r4",
                ModelPartBuilder.create().uv(12, 18).cuboid(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(-1.3F, 0.4F, 0.0F, 0.0F, 0.0F, -0.7679F));

        ModelPartData cube_r5 = Asas.addChild("cube_r5",
                ModelPartBuilder.create().uv(0, 18).cuboid(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(-2.2F, -0.7F, 0.0F, 0.0F, 0.0F, -0.4014F));

        // CAPA
        ModelPartData Capa = modelPartData.addChild("Capa",
                ModelPartBuilder.create(),
                ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r6 = Capa.addChild("cube_r6",
                ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -2.0F, -2.0F, 6.0F, 10.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(3.3F, -11.7F, 2.3F, 0.6458F, 0.0F, 0.0F));

        // TORSO
        ModelPartData Torso = modelPartData.addChild("Torso",
                ModelPartBuilder.create().uv(14, 0).cuboid(1.4F, -12.0F, -2.1F, 4.0F, 7.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        // CABEÇA
        ModelPartData Cabeca = modelPartData.addChild("Cabeca",
                ModelPartBuilder.create().uv(0, 11).cuboid(-3.8F, -3.2F, -2.9F, 5.0F, 4.0F, 3.0F, new Dilation(0.0F)),
                ModelTransform.pivot(4.7F, 10.0F, 0.0F));

        // OSSO EXPOSTO
        ModelPartData OssoExposto = modelPartData.addChild("OssoExposto",
                ModelPartBuilder.create()
                        .uv(24, 18).cuboid(2.0F, -13.9F, -1.7F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                        .uv(26, 6).cuboid(3.6F, -13.9F, -1.7F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        // OSSOS DA PERNA
        ModelPartData OssosPerna = modelPartData.addChild("OssosPerna",
                ModelPartBuilder.create()
                        .uv(20, 24).cuboid(2.1F, -5.0F, -1.6F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                        .uv(24, 24).cuboid(3.6F, -5.0F, -1.6F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r7 = OssosPerna.addChild("cube_r7",
                ModelPartBuilder.create()
                        .uv(26, 3).cuboid(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                        .uv(26, 0).cuboid(0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(3.1F, -1.7F, -0.9F, 0.384F, 0.0F, 0.0F));

        // PÉ
        ModelPartData Pe = modelPartData.addChild("Pe",
                ModelPartBuilder.create()
                        .uv(8, 24).cuboid(-1.1F, -0.8F, -0.8F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                        .uv(14, 24).cuboid(0.4F, -0.8F, -0.8F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(3.2F, 22.6F, -1.2F));

        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void setAngles(GhostEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        // Rotação da cabeça baseada no olhar
        this.Cabeca.yaw = headYaw * 0.017453292F;
        this.Cabeca.pitch = headPitch * 0.017453292F;

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        // Renderiza apenas a raiz - ela renderizará todos os filhos automaticamente
        this.root.render(matrices, vertices, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return root;
    }
}