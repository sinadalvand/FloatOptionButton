package ir.sinadalvand.floatoptionbutton

import android.os.Parcel

internal fun Parcel.writeSuperBoolean(boolean: Boolean){
    writeInt(if(boolean) 1 else 0)
}
internal fun Parcel.readSuperBoolean():Boolean{
    return readInt()==1
}