package edu.pitt.spotify.rest;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import edu.pitt.spotify.utils.DbUtilities;

/**
 * Servlet implementation class get_songs
 */
@WebServlet("/api/get_songs")
public class get_songs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public get_songs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
	
		String songName = "", artistName = "", albumName = "";
		String sql = "";
		
		
		if(request.getParameter("songName") != null){
			
			songName = request.getParameter("songName");
			
			if(!songName.equals("")){
				sql = "SELECT * FROM song WHERE title LIKE '%" + songName + "%';";
				JSONArray songList = new JSONArray();
				
				try {
					DbUtilities db = new DbUtilities();
					ResultSet rs = db.getResultSet(sql);
					while(rs.next()){
						JSONObject song = new JSONObject();
						song.put("song_id", rs.getString("song_id"));
						song.put("title", rs.getString("title"));
						song.put("release_date", rs.getString("release_date"));
						song.put("record_date", rs.getString("record_date"));
						song.put("file_path", rs.getString("file_path"));
						song.put("length", rs.getInt("length"));
						songList.put(song);
					}
					response.getWriter().write(songList.toString());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		else if(request.getParameter("artistName") != null){
			artistName = request.getParameter("artistName");
			if(! artistName.equals("")){
				sql = "SELECT * FROM artist WHERE artist.band_name LIKE '%" + artistName 
				+ "%' OR artist.first_name LIKE '%" + artistName 
				+ "%' OR artist.last_name LIKE '%" + artistName + "%';";
				
				JSONArray artistList = new JSONArray();
				
				try {
					DbUtilities db = new DbUtilities();
					ResultSet rs = db.getResultSet(sql);
					while(rs.next()){
						JSONObject artist = new JSONObject();
						artist.put("artist_id", rs.getString("artist_id"));
						artist.put("first_name", rs.getString("first_name"));
						artist.put("last_name", rs.getString("last_name"));
						artist.put("band_name", rs.getString("band_name"));
						artist.put("bio", rs.getString("bio"));
						artistList.put(artist);
					}
					response.getWriter().write(artistList.toString());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		else if(request.getParameter("albumName")!=null){
			
			albumName = request.getParameter("albumName");
			
			if(!albumName.equals("")){
				sql = "SELECT * FROM album WHERE title LIKE '%" + albumName + "%';";
				
				JSONArray albumList = new JSONArray();
				
				try {
					DbUtilities db = new DbUtilities();
					ResultSet rs = db.getResultSet(sql);
					while(rs.next()){
						JSONObject album = new JSONObject();
						album.put("album_id", rs.getString("album_id"));
						album.put("title", rs.getString("title"));
						album.put("release_date", rs.getString("release_date"));
						album.put("cover_image_path", rs.getString("cover_image_path"));
						album.put("recording_company_name", rs.getString("recording_company_name"));
						album.put("number_of_tracks", rs.getInt("number_of_tracks"));
						album.put("PMRC_rating", rs.getString("PMRC_rating"));
						album.put("length", rs.getDouble("length"));
						albumList.put(album);
					}
					response.getWriter().write(albumList.toString());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if(sql.equals("")){
			
			sql = "SELECT * FROM song;";
			
			JSONArray songList = new JSONArray();
			
			try {
				DbUtilities db = new DbUtilities();
				ResultSet rs = db.getResultSet(sql);
				while(rs.next()){
					JSONObject song = new JSONObject();
					song.put("song_id", rs.getString("song_id"));
					song.put("title", rs.getString("title"));
					song.put("release_date", rs.getString("release_date"));
					song.put("record_date", rs.getString("record_date"));
					song.put("file_path", rs.getString("file_path"));
					song.put("length", rs.getInt("length"));
					songList.put(song);
				}
				response.getWriter().write(songList.toString());
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}