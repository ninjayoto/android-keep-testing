package net.rafaeltoledo.keeptesting;

import android.os.Parcelable;

import net.rafaeltoledo.keeptesting.data.model.User;
import net.rafaeltoledo.keeptesting.ui.adapter.UserAdapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 23, constants = BuildConfig.class)
public class AdapterStateTest {

    @Test
    public void assertThatAdapterIsConsistentAfterRestoring() {
        UserAdapter adapter = new UserAdapter(null, null);
        adapter.addAll(Arrays.asList(
                new User("Johny", "http://example.com/johny.jpg", "Moon", "http://johny.example.com"),
                new User("Michael", "http://example.com/michael.jpg", "Mars", "http://michael.example.com"),
                new User("Thomas", "http://example.com/thomas.jpg", "Pluto", "http://thomas.example.com")
        ), true);

        Parcelable parcelable = adapter.onSaveInstanceState();

        UserAdapter newAdapter = new UserAdapter(null, null);
        newAdapter.onRestoreInstanceState(parcelable);

        assertEquals(adapter.getItemCount(), newAdapter.getItemCount());
        assertEquals(adapter.getItems().get(0).getDisplayName(), newAdapter.getItems().get(0).getDisplayName());
    }
}
