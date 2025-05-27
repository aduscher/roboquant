package org.roboquant.feeds.csv;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Asset;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"},
   d2 = {"Lorg/roboquant/feeds/csv/AssetBuilder;", "", "build", "Lorg/roboquant/common/Asset;", "name", "", "roboquant"}
)
public interface AssetBuilder {
   @NotNull
   Asset build(@NotNull String var1);
}
