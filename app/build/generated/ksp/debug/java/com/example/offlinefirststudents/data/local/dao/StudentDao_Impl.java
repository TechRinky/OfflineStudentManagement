package com.example.offlinefirststudents.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingSource;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.paging.LimitOffsetPagingSource;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.offlinefirststudents.data.local.SyncStatusConverter;
import com.example.offlinefirststudents.data.local.entity.StudentEntity;
import com.example.offlinefirststudents.domain.SyncStatus;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class StudentDao_Impl implements StudentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<StudentEntity> __insertionAdapterOfStudentEntity;

  private final SyncStatusConverter __syncStatusConverter = new SyncStatusConverter();

  private final SharedSQLiteStatement __preparedStmtOfSoftDelete;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  public StudentDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStudentEntity = new EntityInsertionAdapter<StudentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `students` (`id`,`fullName`,`studentClass`,`gender`,`schoolId`,`createdAt`,`updatedAt`,`deleted`,`syncStatus`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StudentEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getFullName());
        statement.bindString(3, entity.getStudentClass());
        statement.bindString(4, entity.getGender());
        statement.bindString(5, entity.getSchoolId());
        statement.bindString(6, entity.getCreatedAt());
        statement.bindString(7, entity.getUpdatedAt());
        final int _tmp = entity.getDeleted() ? 1 : 0;
        statement.bindLong(8, _tmp);
        final String _tmp_1 = __syncStatusConverter.toString(entity.getSyncStatus());
        statement.bindString(9, _tmp_1);
      }
    };
    this.__preparedStmtOfSoftDelete = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE students SET deleted = 1, syncStatus = ?, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM students WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object upsert(final StudentEntity[] students,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfStudentEntity.insert(students);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object softDelete(final String id, final String updatedAt, final SyncStatus status,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfSoftDelete.acquire();
        int _argIndex = 1;
        final String _tmp = __syncStatusConverter.toString(status);
        _stmt.bindString(_argIndex, _tmp);
        _argIndex = 2;
        _stmt.bindString(_argIndex, updatedAt);
        _argIndex = 3;
        _stmt.bindString(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfSoftDelete.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final String id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public PagingSource<Integer, StudentEntity> paging() {
    final String _sql = "SELECT * FROM students WHERE deleted = 0 ORDER BY updatedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new LimitOffsetPagingSource<StudentEntity>(_statement, __db, "students") {
      @Override
      @NonNull
      protected List<StudentEntity> convertRows(@NonNull final Cursor cursor) {
        final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(cursor, "id");
        final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(cursor, "fullName");
        final int _cursorIndexOfStudentClass = CursorUtil.getColumnIndexOrThrow(cursor, "studentClass");
        final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(cursor, "gender");
        final int _cursorIndexOfSchoolId = CursorUtil.getColumnIndexOrThrow(cursor, "schoolId");
        final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(cursor, "createdAt");
        final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(cursor, "updatedAt");
        final int _cursorIndexOfDeleted = CursorUtil.getColumnIndexOrThrow(cursor, "deleted");
        final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(cursor, "syncStatus");
        final List<StudentEntity> _result = new ArrayList<StudentEntity>(cursor.getCount());
        while (cursor.moveToNext()) {
          final StudentEntity _item;
          final String _tmpId;
          _tmpId = cursor.getString(_cursorIndexOfId);
          final String _tmpFullName;
          _tmpFullName = cursor.getString(_cursorIndexOfFullName);
          final String _tmpStudentClass;
          _tmpStudentClass = cursor.getString(_cursorIndexOfStudentClass);
          final String _tmpGender;
          _tmpGender = cursor.getString(_cursorIndexOfGender);
          final String _tmpSchoolId;
          _tmpSchoolId = cursor.getString(_cursorIndexOfSchoolId);
          final String _tmpCreatedAt;
          _tmpCreatedAt = cursor.getString(_cursorIndexOfCreatedAt);
          final String _tmpUpdatedAt;
          _tmpUpdatedAt = cursor.getString(_cursorIndexOfUpdatedAt);
          final boolean _tmpDeleted;
          final int _tmp;
          _tmp = cursor.getInt(_cursorIndexOfDeleted);
          _tmpDeleted = _tmp != 0;
          final SyncStatus _tmpSyncStatus;
          final String _tmp_1;
          _tmp_1 = cursor.getString(_cursorIndexOfSyncStatus);
          _tmpSyncStatus = __syncStatusConverter.fromString(_tmp_1);
          _item = new StudentEntity(_tmpId,_tmpFullName,_tmpStudentClass,_tmpGender,_tmpSchoolId,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeleted,_tmpSyncStatus);
          _result.add(_item);
        }
        return _result;
      }
    };
  }

  @Override
  public Object getById(final String id, final Continuation<? super StudentEntity> $completion) {
    final String _sql = "SELECT * FROM students WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<StudentEntity>() {
      @Override
      @Nullable
      public StudentEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "fullName");
          final int _cursorIndexOfStudentClass = CursorUtil.getColumnIndexOrThrow(_cursor, "studentClass");
          final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
          final int _cursorIndexOfSchoolId = CursorUtil.getColumnIndexOrThrow(_cursor, "schoolId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "deleted");
          final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "syncStatus");
          final StudentEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpFullName;
            _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            final String _tmpStudentClass;
            _tmpStudentClass = _cursor.getString(_cursorIndexOfStudentClass);
            final String _tmpGender;
            _tmpGender = _cursor.getString(_cursorIndexOfGender);
            final String _tmpSchoolId;
            _tmpSchoolId = _cursor.getString(_cursorIndexOfSchoolId);
            final String _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
            final String _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getString(_cursorIndexOfUpdatedAt);
            final boolean _tmpDeleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfDeleted);
            _tmpDeleted = _tmp != 0;
            final SyncStatus _tmpSyncStatus;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfSyncStatus);
            _tmpSyncStatus = __syncStatusConverter.fromString(_tmp_1);
            _result = new StudentEntity(_tmpId,_tmpFullName,_tmpStudentClass,_tmpGender,_tmpSchoolId,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeleted,_tmpSyncStatus);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<StudentEntity>> pending(final List<? extends SyncStatus> states) {
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM students WHERE syncStatus IN (");
    final int _inputSize = states.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") OR deleted = 1");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (SyncStatus _item : states) {
      final String _tmp = __syncStatusConverter.toString(_item);
      _statement.bindString(_argIndex, _tmp);
      _argIndex++;
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"students"}, new Callable<List<StudentEntity>>() {
      @Override
      @NonNull
      public List<StudentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "fullName");
          final int _cursorIndexOfStudentClass = CursorUtil.getColumnIndexOrThrow(_cursor, "studentClass");
          final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
          final int _cursorIndexOfSchoolId = CursorUtil.getColumnIndexOrThrow(_cursor, "schoolId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "deleted");
          final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "syncStatus");
          final List<StudentEntity> _result = new ArrayList<StudentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StudentEntity _item_1;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpFullName;
            _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            final String _tmpStudentClass;
            _tmpStudentClass = _cursor.getString(_cursorIndexOfStudentClass);
            final String _tmpGender;
            _tmpGender = _cursor.getString(_cursorIndexOfGender);
            final String _tmpSchoolId;
            _tmpSchoolId = _cursor.getString(_cursorIndexOfSchoolId);
            final String _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
            final String _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getString(_cursorIndexOfUpdatedAt);
            final boolean _tmpDeleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfDeleted);
            _tmpDeleted = _tmp_1 != 0;
            final SyncStatus _tmpSyncStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfSyncStatus);
            _tmpSyncStatus = __syncStatusConverter.fromString(_tmp_2);
            _item_1 = new StudentEntity(_tmpId,_tmpFullName,_tmpStudentClass,_tmpGender,_tmpSchoolId,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeleted,_tmpSyncStatus);
            _result.add(_item_1);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object pendingOnce(final List<? extends SyncStatus> states,
      final Continuation<? super List<StudentEntity>> $completion) {
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM students WHERE syncStatus IN (");
    final int _inputSize = states.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") OR deleted = 1");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (SyncStatus _item : states) {
      final String _tmp = __syncStatusConverter.toString(_item);
      _statement.bindString(_argIndex, _tmp);
      _argIndex++;
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<StudentEntity>>() {
      @Override
      @NonNull
      public List<StudentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "fullName");
          final int _cursorIndexOfStudentClass = CursorUtil.getColumnIndexOrThrow(_cursor, "studentClass");
          final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
          final int _cursorIndexOfSchoolId = CursorUtil.getColumnIndexOrThrow(_cursor, "schoolId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "deleted");
          final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "syncStatus");
          final List<StudentEntity> _result = new ArrayList<StudentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StudentEntity _item_1;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpFullName;
            _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            final String _tmpStudentClass;
            _tmpStudentClass = _cursor.getString(_cursorIndexOfStudentClass);
            final String _tmpGender;
            _tmpGender = _cursor.getString(_cursorIndexOfGender);
            final String _tmpSchoolId;
            _tmpSchoolId = _cursor.getString(_cursorIndexOfSchoolId);
            final String _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
            final String _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getString(_cursorIndexOfUpdatedAt);
            final boolean _tmpDeleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfDeleted);
            _tmpDeleted = _tmp_1 != 0;
            final SyncStatus _tmpSyncStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfSyncStatus);
            _tmpSyncStatus = __syncStatusConverter.fromString(_tmp_2);
            _item_1 = new StudentEntity(_tmpId,_tmpFullName,_tmpStudentClass,_tmpGender,_tmpSchoolId,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeleted,_tmpSyncStatus);
            _result.add(_item_1);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
