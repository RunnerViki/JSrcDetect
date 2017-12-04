package com.viki.java.utils;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/20 17:36
 */
public class TextSimilarityUtils {

    public static double similar(String text1,String text2)
    {

        return editDistance(text1,text2);
    }


    private static double editDistance(String text1, String text2) {
        int maxTextLength = Math.max(text1.length(), text2.length());
        if(maxTextLength == 0){
            return 1.0;
        }
        int[] costs = new int[text2.length() + 1];
        for (int i = 0; i <= text1.length(); i++) {
            int previousValue = i;
            for (int j = 0; j <= text2.length(); j++) {
                if (i == 0) {
                    costs[j] = j;
                }
                else if (j > 0) {
                    int useValue = costs[j - 1];
                    if (text1.charAt(i - 1) != text2.charAt(j - 1)) {
                        useValue = Math.min(Math.min(useValue, previousValue), costs[j]) + 1;
                    }
                    costs[j - 1] = previousValue;
                    previousValue = useValue;

                }
            }
            if (i > 0) {
                costs[text2.length()] = previousValue;
            }
        }
        return (1 - costs[text2.length()] / (double)maxTextLength);
    }

    public static void main(String[] args) {
        String text1 = StringUtils.filterSymbols(StringUtils.filterEmoji("足-篮-分-析-胶-流—群：                     (8-9-0-5-5-5-5),    专/业/免/费/带/队/微/信/;     (dawin66)"));
        String text2 = StringUtils.filterSymbols(StringUtils.filterEmoji("足-篮-分-析-胶-流—群：                   （8-9-0-5-5-5-5）专/业//免/费/带/队/微/信/；（dawin66）"));

        String a1 = "欧青参考:\n" +
                "哈萨克斯坦位于中亚，加入欧足联后一直在找机会，威尔斯欧洲小国家，队伍实力不足！要想出线，就要吃掉对方！威尔斯是欧洲国家，自然看不起亚洲球队！一个防守，一个进攻，根本打不出来大球！所以咱们买小球没有错！";
        String a2 = "波兰近期主场出色，连续8个主场各项赛事未尝败绩，上轮友谊赛即使缺少大腿莱万也能战平与墨西哥排名相当的乌拉圭（人家苏亚雷斯也不在啊），本战莱万未知能否复出，墨西哥的小豌豆上场也受伤了，本场也未知能否回归，那么暂时就当作是指数平衡吧。墨西哥近期客场不败率也很高，输球对手基本都是世界前三球队。波兰连续4个国际友谊赛不胜，对手当中竟然还包括立陶宛这种弱旅，平手盘显然是诱墨西哥，墨西哥还在升水啊，别中招了";

        long begin = System.currentTimeMillis();
        double score1pk1 = similar(a1, a2);
        long end = System.currentTimeMillis();
        System.out.println("haoshi = "+(score1pk1 - 0));
    }
}