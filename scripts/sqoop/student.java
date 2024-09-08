// ORM class for table 'student'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Sun Jun 07 17:05:09 CST 2020
// For connector: org.apache.sqoop.manager.MySQLManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class student extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("classid", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        student.this.classid = (String)value;
      }
    });
    setters.put("data", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        student.this.data = (String)value;
      }
    });
    setters.put("math", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        student.this.math = (String)value;
      }
    });
    setters.put("chinese", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        student.this.chinese = (String)value;
      }
    });
    setters.put("englist", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        student.this.englist = (String)value;
      }
    });
  }
  public student() {
    init0();
  }
  private String classid;
  public String get_classid() {
    return classid;
  }
  public void set_classid(String classid) {
    this.classid = classid;
  }
  public student with_classid(String classid) {
    this.classid = classid;
    return this;
  }
  private String data;
  public String get_data() {
    return data;
  }
  public void set_data(String data) {
    this.data = data;
  }
  public student with_data(String data) {
    this.data = data;
    return this;
  }
  private String math;
  public String get_math() {
    return math;
  }
  public void set_math(String math) {
    this.math = math;
  }
  public student with_math(String math) {
    this.math = math;
    return this;
  }
  private String chinese;
  public String get_chinese() {
    return chinese;
  }
  public void set_chinese(String chinese) {
    this.chinese = chinese;
  }
  public student with_chinese(String chinese) {
    this.chinese = chinese;
    return this;
  }
  private String englist;
  public String get_englist() {
    return englist;
  }
  public void set_englist(String englist) {
    this.englist = englist;
  }
  public student with_englist(String englist) {
    this.englist = englist;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof student)) {
      return false;
    }
    student that = (student) o;
    boolean equal = true;
    equal = equal && (this.classid == null ? that.classid == null : this.classid.equals(that.classid));
    equal = equal && (this.data == null ? that.data == null : this.data.equals(that.data));
    equal = equal && (this.math == null ? that.math == null : this.math.equals(that.math));
    equal = equal && (this.chinese == null ? that.chinese == null : this.chinese.equals(that.chinese));
    equal = equal && (this.englist == null ? that.englist == null : this.englist.equals(that.englist));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof student)) {
      return false;
    }
    student that = (student) o;
    boolean equal = true;
    equal = equal && (this.classid == null ? that.classid == null : this.classid.equals(that.classid));
    equal = equal && (this.data == null ? that.data == null : this.data.equals(that.data));
    equal = equal && (this.math == null ? that.math == null : this.math.equals(that.math));
    equal = equal && (this.chinese == null ? that.chinese == null : this.chinese.equals(that.chinese));
    equal = equal && (this.englist == null ? that.englist == null : this.englist.equals(that.englist));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.classid = JdbcWritableBridge.readString(1, __dbResults);
    this.data = JdbcWritableBridge.readString(2, __dbResults);
    this.math = JdbcWritableBridge.readString(3, __dbResults);
    this.chinese = JdbcWritableBridge.readString(4, __dbResults);
    this.englist = JdbcWritableBridge.readString(5, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.classid = JdbcWritableBridge.readString(1, __dbResults);
    this.data = JdbcWritableBridge.readString(2, __dbResults);
    this.math = JdbcWritableBridge.readString(3, __dbResults);
    this.chinese = JdbcWritableBridge.readString(4, __dbResults);
    this.englist = JdbcWritableBridge.readString(5, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(classid, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(data, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(math, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(chinese, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(englist, 5 + __off, 12, __dbStmt);
    return 5;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(classid, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(data, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(math, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(chinese, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(englist, 5 + __off, 12, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.classid = null;
    } else {
    this.classid = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.data = null;
    } else {
    this.data = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.math = null;
    } else {
    this.math = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.chinese = null;
    } else {
    this.chinese = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.englist = null;
    } else {
    this.englist = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.classid) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, classid);
    }
    if (null == this.data) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, data);
    }
    if (null == this.math) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, math);
    }
    if (null == this.chinese) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, chinese);
    }
    if (null == this.englist) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, englist);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.classid) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, classid);
    }
    if (null == this.data) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, data);
    }
    if (null == this.math) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, math);
    }
    if (null == this.chinese) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, chinese);
    }
    if (null == this.englist) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, englist);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(classid==null?"null":classid, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(data==null?"null":data, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(math==null?"null":math, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(chinese==null?"null":chinese, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(englist==null?"null":englist, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(classid==null?"null":classid, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(data==null?"null":data, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(math==null?"null":math, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(chinese==null?"null":chinese, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(englist==null?"null":englist, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.classid = null; } else {
      this.classid = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.data = null; } else {
      this.data = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.math = null; } else {
      this.math = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.chinese = null; } else {
      this.chinese = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.englist = null; } else {
      this.englist = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.classid = null; } else {
      this.classid = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.data = null; } else {
      this.data = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.math = null; } else {
      this.math = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.chinese = null; } else {
      this.chinese = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.englist = null; } else {
      this.englist = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    student o = (student) super.clone();
    return o;
  }

  public void clone0(student o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("classid", this.classid);
    __sqoop$field_map.put("data", this.data);
    __sqoop$field_map.put("math", this.math);
    __sqoop$field_map.put("chinese", this.chinese);
    __sqoop$field_map.put("englist", this.englist);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("classid", this.classid);
    __sqoop$field_map.put("data", this.data);
    __sqoop$field_map.put("math", this.math);
    __sqoop$field_map.put("chinese", this.chinese);
    __sqoop$field_map.put("englist", this.englist);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
