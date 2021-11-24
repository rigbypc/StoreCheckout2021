d <- read.table('~/eclipse-workspace-09/store-checkout/logs/Analytics.log', sep = ',')
summary(d)

#get enabled
enabled <- d[d$V3 == " Discount applied", ]
summary(enabled)

#get disabled and a sample of same length as enabled
disabled <- d[d$V3 == " No Discount", ]
rndDisabled <- disabled[sample(nrow(disabled), length(enabled$V3)), ]
summary(rndDisabled)

#statistical test
wilcox.test(enabled$V4, rndDisabled$V4)

boxplot(enabled$V4, rndDisabled$V4)

#median difference
median(enabled$V4) - median(rndDisabled$V4)

#median times
median(enabled$V4)/median(rndDisabled$V4)

