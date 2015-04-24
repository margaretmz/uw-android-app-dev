package edu.uw.aad.mzm.sample.provider.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by Margaret on 4/21/15.
 */
public class AndroidVersionProvider extends ContentProvider {

    private static final int VERSION = 100;
    private static final int VERSION_ID = 200;

    private static final UriMatcher sUriMatcher = createUriMatcher();

    private static UriMatcher createUriMatcher() {

        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = AndroidContract.CONTENT_AUTHORITY;
        uriMatcher.addURI(authority, AndroidContract.PATH_VERSION, VERSION);
        uriMatcher.addURI(authority, AndroidContract.PATH_VERSION + "/#", VERSION_ID);

        return uriMatcher;
    }

    private AndroidDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new AndroidDbHelper(getContext());
        // Content Provider created
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        // Use SQLiteQueryBuilder for querying db
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // Set the table name
        queryBuilder.setTables(AndroidContract.Version.TABLE_NAME);

        // Record id
        String id;

        // Match Uri pattern
        int uriType = sUriMatcher.match(uri);

        switch (uriType) {
            case VERSION:
                break;
            case VERSION_ID:
                selection = AndroidContract.Version.ID + " = ? ";
                id = uri.getLastPathSegment();
                selectionArgs = new String[] {id};
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);

        }

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(
                db,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
            );

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {

        switch ((sUriMatcher.match(uri))) {
            case VERSION:
                return AndroidContract.Version.CONTENT_TYPE;
            case VERSION_ID:
                return AndroidContract.Version.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);

        }

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int uriType = sUriMatcher.match(uri);
        long rowId;

        switch (uriType) {
            case VERSION:
                rowId = db.insertOrThrow(AndroidContract.Version.TABLE_NAME, null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(AndroidContract.Version.CONTENT_URI, rowId);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int uriType = sUriMatcher.match(uri);
        int deletionCount = 0;

        switch (uriType) {
            case VERSION:
                deletionCount = db.delete(AndroidContract.Version.TABLE_NAME, selection, selectionArgs);
                break;
            case VERSION_ID:
                String id = uri.getLastPathSegment();
                deletionCount = db.delete(
                        AndroidContract.Version.TABLE_NAME,
                        AndroidContract.Version.ID + " = " + id +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), // append selection to query if selection is not empty
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return deletionCount;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int uriType = sUriMatcher.match(uri);
        int updateCount = 0;
        switch (uriType) {
            case VERSION:
                updateCount = db.update(AndroidContract.Version.TABLE_NAME, values, selection, selectionArgs);
                break;
            case VERSION_ID:
                String id = uri.getLastPathSegment();
                updateCount = db.update(AndroidContract.Version.TABLE_NAME,
                                        values,
                                        AndroidContract.Version.ID + " =" + id +
                                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), // append selection to query if selection is not empty
                                        selectionArgs);
                break;
            default: throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return updateCount;
    }

}
