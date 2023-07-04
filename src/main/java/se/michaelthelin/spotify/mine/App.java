package se.michaelthelin.spotify.mine;

import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;

import java.io.IOException;
import java.util.List;

/**
 * @author Aurelijus Jurkus
 * @since 2023-07-04
 */
public class App {

  public static void main(String[] args) throws IOException, ParseException, SpotifyWebApiException {

//    SpotifyApi api = getApi();
    SpotifyApi appApi = getAppApi();

    Artist artist = appApi
      .getArtist("00FQb4jTyendYWaN8pK0wa")
      .build()
      .execute();

    String artistId = artist.getId();

    Paging<AlbumSimplified> albumSimplifiedPaging = appApi
      .getArtistsAlbums(artistId)//Maximum: 50.
      .build()
      .execute();

    List<AlbumSimplified> allAlbums =appApi
      .getArtistsAllAlbums(artistId);////////
//      .build()
//      .execute();

  }

/*  private static SpotifyApi getApi() {
    final se.michaelthelin.spotify.SpotifyApi api = new se.michaelthelin.spotify.SpotifyApi.Builder()
      .setAccessToken("dsfghjk")
      .build();
    return api;
  }*/

  private static SpotifyApi getAppApi() throws IOException, ParseException, SpotifyWebApiException {
    final SpotifyApi appApi = new SpotifyApi.Builder()
      .setClientId("zzz")
      .setClientSecret("zzz")
      .build();

    ClientCredentials clientCredentials = appApi.clientCredentials().build().execute();
    String accessToken = clientCredentials.getAccessToken();
    appApi.setAccessToken(accessToken);
    return appApi;
  }

}
