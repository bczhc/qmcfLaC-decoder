package pers.zhc.tools.kangxiconverter

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.EditText
import java.util.regex.Pattern

object KangxiConverter {
    private const val kangxiRadicals =
        "⼀⼁⼂⼃⼄⼅⼆⼇⼈⼉⼊⼋⼌⼍⼎⼏⼐⼑⼒⼓⼔⼕⼖⼗⼘⼙⼚⼛⼜⼝⼞⼟⼠⼡⼢⼣⼤⼥⼦⼧⼨⼩⼪⼫⼬⼭⼮⼯⼰⼱⼲⼳⼴⼵⼶⼷⼸⼹⼺⼻⼼⼽⼾⼿⽀⽁⽂⽃⽄⽅⽆⽇⽈⽉⽊⽋⽌⽍⽎⽏⽐⽑⽒⽓⽔⽕⽖⽗⽘⽙⽚⽛⽜⽝⽞⽟⽠⽡⽢⽣⽤⽥⽦⽧⽨⽩⽪⽫⽬⽭⽮⽯⽰⽱⽲⽳⽴⽵⽶⽷⽸⽹⽺⽻⽼⽽⽾⽿⾀⾁⾂⾃⾄⾅⾆⾇⾈⾉⾊⾋⾌⾍⾎⾏⾐⾑⾒⾓⾔⾕⾖⾗⾘⾙⾚⾛⾜⾝⾞⾟⾠⾡⾢⾣⾤⾥⾦⾧⾨⾩⾪⾫⾬⾭⾮⾯⾰⾱⾲⾳⾴⾵⾶⾷⾸⾹⾺⾻⾼⾽⾾⾿⿀⿁⿂⿃⿄⿅⿆⿇⿈⿉⿊⿋⿌⿍⿎⿏⿐⿑⿒⿓⿔⿕"
    private val kangxiRadicalsArr = kangxiRadicals.split("".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    private const val normalHans =
        "一丨丶丿乙亅二亠人儿入八冂冖冫几凵刀力勹匕匚匸十卜卩厂厶又口口土士夂夊夕大女子宀寸小尢尸屮山巛工己巾干幺广廴廾弋弓彐彡彳心戈户手支攴文斗斤方无日曰月木欠止歹殳毋比毛氏气水火爪父爻爿片牙牛犬玄玉瓜瓦甘生用田疋疒癶白皮皿目矛矢石示禸禾穴立竹米糸缶网羊羽老而耒耳聿肉臣自至臼舌舛舟艮色艸虍虫血行衣襾見角言谷豆豕豸貝赤走足身車辛辰辵邑酉采里金長門阜隶隹雨青非面革韋韭音頁風飛食首香馬骨高髟鬥鬯鬲鬼魚鳥鹵鹿麥麻黃黍黑黹黽鼎鼓鼠鼻齊齒龍龜龠"
    private val normalHansArr = normalHans.split("".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

    fun hasKangxiRadicals(str: String): Boolean {
        return str.matches((".*[" + kangxiRadicals + "].*").toRegex())
    }

    fun hasNormalKangXiChars(str: String): Boolean {
        return str.matches((".*[" + normalHans + "].*").toRegex())
    }

    @JvmStatic
    fun KangXi2Normal(str: String): String {
        var str = str
        if (hasKangxiRadicals(str)) {
            var i = -1
            while (i < kangxiRadicals.length - 1) {
                i++
                str = str.replace(kangxiRadicalsArr[i], normalHansArr[i])
            }
        }
        return str
    }

    @JvmStatic
    fun normal2KangXi(str: String): String {
        var str = str
        if (hasNormalKangXiChars(str)) {
            var i = -1
            while (i < kangxiRadicals.length - 1) {
                i++
                str = str.replace(normalHansArr[i], kangxiRadicalsArr[i])
            }
        }
        return str
    }

    @JvmStatic
    fun markKangxiRadicalsEditText(et: EditText) {
        val inputText = et.text.toString()
        if (!hasKangxiRadicals(inputText)) return
        val spannableString = SpannableString(inputText)

        val regex = "[" + kangxiRadicals + "]" // 你的正则表达式

        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(inputText)
        while (matcher.find()) {
            val start = matcher.start()
            val end = matcher.end()
            val colorSpan = ForegroundColorSpan(Color.RED) // 高亮颜色
            spannableString.setSpan(colorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        et.setText(spannableString)
        et.setSelection(inputText.length) // 将光标移至末尾
    }

    @JvmStatic
    fun markNormalHansEditText(et: EditText) {
        val inputText = et.text.toString()
        if (!hasNormalKangXiChars(inputText)) return
        val spannableString = SpannableString(inputText)

        val regex = "[" + normalHans + "]" // 你的正则表达式

        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(inputText)
        while (matcher.find()) {
            val start = matcher.start()
            val end = matcher.end()
            val colorSpan = ForegroundColorSpan(Color.GREEN) // 高亮颜色
            spannableString.setSpan(colorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        et.setText(spannableString)
        et.setSelection(inputText.length) // 将光标移至末尾
    }
}
