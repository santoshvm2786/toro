/*
 * Copyright (c) 2022 Nam Nguyen, nam@ene.im
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.ene.toro.exoplayer;

import com.google.android.exoplayer2.ForwardingPlayer;
import com.google.android.exoplayer2.Player;
import im.ene.toro.ToroPlayer;
import im.ene.toro.widget.PressablePlayerSelector;

public class ExoForwardingPlayer extends ForwardingPlayer {
  private final PressablePlayerSelector playerSelector;
  private final ToroPlayer toroPlayer;

  /**
   * Creates a new instance that forwards all operations to {@code player}.
   */
  public ExoForwardingPlayer(Player player, PressablePlayerSelector playerSelector,
      ToroPlayer toroPlayer) {
    super(player);
    this.playerSelector = playerSelector;
    this.toroPlayer = toroPlayer;
  }

  @Override public void setPlayWhenReady(boolean playWhenReady) {
    super.setPlayWhenReady(playWhenReady);
    if (playWhenReady) {
      playerSelector.toPlay(toroPlayer.getPlayerOrder());
    } else {
      playerSelector.toPause(toroPlayer.getPlayerOrder());
    }
  }
}
