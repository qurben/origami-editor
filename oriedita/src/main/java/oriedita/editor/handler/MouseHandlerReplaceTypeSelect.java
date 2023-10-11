package oriedita.editor.handler;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import oriedita.editor.canvas.MouseMode;
import oriedita.editor.databinding.ApplicationModel;
import origami.Epsilon;
import origami.crease_pattern.CustomLineTypes;
import origami.crease_pattern.element.LineColor;
import origami.crease_pattern.element.LineSegment;
import origami.crease_pattern.element.Point;

@ApplicationScoped
@Handles(MouseMode.REPLACE_LINE_TYPE_SELECT_72)
public class MouseHandlerReplaceTypeSelect extends BaseMouseHandlerBoxSelect {

    private ApplicationModel applicationModel;

    @Inject
    public MouseHandlerReplaceTypeSelect(ApplicationModel applicationModel) {
        this.applicationModel = applicationModel;
    }

    public void mouseReleased(Point p0){
        super.mouseReleased(p0);
        d.getLineStep().clear();

        Point p = new Point();
        p.set(d.getCamera().TV2object(p0));

        CustomLineTypes from = applicationModel.getCustomFromLineType();
        CustomLineTypes to = applicationModel.getCustomToLineType();

        if (selectionStart.distance(p0) > Epsilon.UNKNOWN_1EN6) {//現状では赤を赤に変えたときもUNDO用に記録されてしまう20161218
            if (d.insideToReplaceType(selectionStart, p0, from, to)) {
                d.fix2();
                d.record();
            }
        } else {//現状では赤を赤に変えたときもUNDO用に記録されてしまう20161218
            if (d.getFoldLineSet().closestLineSegmentDistance(p) < d.getSelectionDistance()) {//点pに最も近い線分の番号での、その距離を返す	public double closestLineSegmentDistance(Ten p)
                LineSegment s = d.getFoldLineSet().closestLineSegmentSearch(p);

                d.getFoldLineSet().deleteLine(s);

                switch (from){
                    case ANY:
                        s.setColor(LineColor.fromNumber(to.getReplaceToTypeNumber()));
                        break;
                    case EGDE:
                        if (s.getColor() == LineColor.BLACK_0) {
                            s.setColor(LineColor.fromNumber(to.getReplaceToTypeNumber()));
                        }
                        break;
                    case MANDV:
                        if (s.getColor() == LineColor.RED_1 || s.getColor() == LineColor.BLUE_2) {
                            s.setColor(LineColor.fromNumber(to.getReplaceToTypeNumber()));
                        }
                        break;
                    case MOUNTAIN:
                    case VALLEY:
                    case AUX:
                        if (s.getColor() == LineColor.fromNumber(from.getNumber() - 1)) {
                            s.setColor(LineColor.fromNumber(to.getReplaceToTypeNumber()));
                        }
                        break;
                    default:
                        break;
                }
                d.addLineSegment(s);
                d.record();
            }
        }
    }

}
