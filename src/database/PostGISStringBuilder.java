package database;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector3d;

import org.postgis.Polygon;

import loaders.Telemetry;

public class PostGISStringBuilder {
	
	private StringBuilder sb = new StringBuilder();
	
	public PostGISStringBuilder() {
		
	}
	
	public String point3d(double x, double y, double z) {
		sb.setLength(0);
		sb.append("POINT(").append(x).append(" ").append(y).append(" ").append(z).append(")");
		return sb.toString();
	}
	
	public String pointGeometry3D(Telemetry telemetry) {
		return point3d(telemetry.coordinates.x, telemetry.coordinates.y, telemetry.coordinates.z);
	}
	
	public String point2d(Vector2d point) {
		return point2d(point.x, point.y);
	}
	
	public String point2d(double x, double y) {
		sb.setLength(0);
		sb.append("POINT(").append(x).append(" ").append(y).append(")");
		return sb.toString();
	}
	
	/**
	 * Only works for a polygon with 4 corners
	 * @param polygon
	 * @return
	 */
	public String polygon2d4corners(Vector2d [] polygon) {
		sb.setLength(0);
		sb.append("POLYGON((").append(polygon[0].x).append(" ").append(polygon[0].y).append(", ")
		.append(polygon[1].x).append(" ").append(polygon[1].y).append(", ")
		.append(polygon[2].x).append(" ").append(polygon[2].y).append(", ")
		.append(polygon[3].x).append(" ").append(polygon[3].y).append(", ")
		.append(polygon[0].x).append(" ").append(polygon[0].y)
		.append("))");
		return sb.toString();
	}
	
	/**
	 * Only works for a polygon with 4 corners
	 * Possibly replaceable by polygo2dFrom3dVector
	 * @param polygon
	 * @return
	 */
	public String polygon2d4cornersFrom3dVector(Vector3d [] polygon) {
		if (polygon == null) {
			return "POLYGON((0 0, 1 1, 2 2, 0 0))";
		}
		//!TODO duplicate code as polygon2d(Vector2d[]). Needs refactoring.
		sb.setLength(0);
		sb.append("POLYGON((").append(polygon[0].x).append(" ").append(polygon[0].y).append(", ")
		.append(polygon[1].x).append(" ").append(polygon[1].y).append(", ")
		.append(polygon[2].x).append(" ").append(polygon[2].y).append(", ")
		.append(polygon[3].x).append(" ").append(polygon[3].y).append(", ")
		.append(polygon[0].x).append(" ").append(polygon[0].y)
		.append("))");
		return sb.toString();
	}
	
	/**
	 * Constructs a PostGIS polygon from an array of 3d vectors representing the polygon's vertices. 
	 * Omits the z coordinate.
	 * @param polygon Array of vectors representing the polygon's vertices. The z coordinate is omitted.
	 * @return String representing a PostGIS polygon.
	 */
	public String polygo2dFrom3dVector(Vector3d [] polygon) {
		sb.setLength(0);
		sb.append("POLYGON((");
		for (int i = 0; i < polygon.length; i ++) {
			sb.append(polygon[i].x).append(" ").append(polygon[i].y).append(", ");
		}
		sb.append(polygon[0].x).append(" ").append(polygon[0].y).append("))");
		return sb.toString();
	}
	
	public String triangleGeometry2d(Vector2d [] triangle) {
		sb.setLength(0);
		sb.append("POLYGON((").append(triangle[0].x).append(" ").append(triangle[0].y).append(", ")
		.append(triangle[1].x).append(" ").append(triangle[1].y).append(", ")
		.append(triangle[2].x).append(" ").append(triangle[2].y).append(", ")
		.append(triangle[0].x).append(" ").append(triangle[0].y)
		.append("))");
		return sb.toString();
	}
}

























