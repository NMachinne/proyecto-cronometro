package proyecto.cronometro.cronometro.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import proyecto.cronometro.cronometro.model.Conection.MariaDBConnection;
import proyecto.cronometro.cronometro.model.Entity.Cronometro;

public class cronometroDAO extends Cronometro{

	private final static String INSERT = "INSERT INTO tiempo (vuelta,horas,minutos,segundos,milisegundos) VALUES (NULL,?,?,?,?)";
	private final static String SELECTALLBYID = "SELECT vuelta,horas,minutos,segundos,milisegundos FROM cronometro WHERE vuelta=?";

	public cronometroDAO( int horas, int minutos, int segundos, int miliSegundos) {
		super(horas, minutos, segundos, miliSegundos);
	}
	
	public void insertTime(Cronometro crono) {
		try {
			create(INSERT, crono);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Collection<Cronometro> getAllbyId(Cronometro crono) throws SQLException {
		Collection<Cronometro> c = read(SELECTALLBYID, crono);
		return c;
	}

	/**
	 * prepara la conexion a mysql, pasandole la sentencia sql
	 * 
	 * @param sql
	 * @throws SQLException
	 */
	public static void create(String sql, Cronometro crono) throws SQLException {
		Connection conn = MariaDBConnection.getConnection();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, crono.horas);
			ps.setInt(2, crono.minutos);
			ps.setInt(3, crono.segundos);
			ps.setInt(4, crono.miliSegundos);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
		}
	}

	public Collection<Cronometro> read(String sql, Cronometro crono) {
		Connection conn = MariaDBConnection.getConnection();
		PreparedStatement pst;
		Collection<Cronometro> result = new ArrayList<Cronometro>();
		ResultSet rs;
		if (conn != null) {
			try {
				pst = conn.prepareStatement(sql);
				if (pst.execute()) {
					rs = pst.executeQuery();
					while (rs.next()) {
						crono.setVuelta(rs.getInt("vuelta"));
						crono.setHoras(rs.getInt("horas"));
						crono.setMinutos(rs.getInt("minutos"));
						crono.setSegundos(rs.getInt("segundos"));
						crono.setMiliSegundos(rs.getInt("milisegundos"));
						result.add(crono);
					}
					rs.close();
				}
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}